package net.todream.uni_translate.uni_translate.utils;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.mapper.CacheMapper;

@Component
public class TranslateCacheUtils {

    public static final String TRANSLATE_CACHE_KEY_PREFIX = "translate";

    public static final Duration TRANSLATE_CACHE_EXPIRE_SEC = Duration.ofDays(1);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private CacheMapper cacheMapper;

    @Resource
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 翻译缓存实现
     * @param md5
     * @param fn
     * @return
     */
    public TranslateClientOutDto getOrSet(String md5, Supplier<TranslateClientOutDto> fn) {
        TranslateClientOutDto out;
        String keyName = getKeyName(md5);
        Object outObj = redisTemplate.opsForValue().get(keyName);
        if (outObj == null) {
            Optional<String> dbOut = cacheMapper.getCacheContent(md5);
            if (dbOut.isEmpty()) {
                out = fn.get();
            } else {
                try {
                    out = objectMapper.readValue(dbOut.get(), TranslateClientOutDto.class);
                    out.setIsCache(true);
                } catch (Exception e) {
                    throw new TranslateException("解析数据库缓存失败", e);
                }
            }
            redisTemplate.opsForValue().set(keyName, out, Duration.ofSeconds(30 * 60));
            return out;
        }
        redisTemplate.expire(keyName, TRANSLATE_CACHE_EXPIRE_SEC);
        out = (TranslateClientOutDto) outObj;
        out.setIsCache(true);
        return out;
    }

    /**
     * 获取key
     * @param md5
     * @return
     */
    public String getKeyName(String md5) {
        return TRANSLATE_CACHE_KEY_PREFIX + ":" + md5;
    }

    /**
     * 获取计算的md5值
     * @param in
     * @return
     */
    public String getMd5(TranslateClientInDto in, Boolean calcPlatform) {
        StringBuilder sb = new StringBuilder();
        sb.append(TRANSLATE_CACHE_KEY_PREFIX).append(":");
        StringBuilder keyBody = new StringBuilder();
        String from = in.getFrom();
        if (from.isEmpty()) {
            from = "auto";
        }
        if (calcPlatform) {
            keyBody.append(in.getPlatform()).append("_");
            sb.append(in.getPlatform()).append(":");
        }

        keyBody.append(from).append("_")
                .append(in.getTo()).append("_")
                .append(in.getText());

        sb.append(keyBody.toString());
        return Common.md5(sb.toString());
    }
}
