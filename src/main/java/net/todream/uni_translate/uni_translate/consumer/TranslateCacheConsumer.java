package net.todream.uni_translate.uni_translate.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.todream.uni_translate.uni_translate.dto.TranslateWithReturnInDto;
import net.todream.uni_translate.uni_translate.entity.Cache;
import net.todream.uni_translate.uni_translate.mapper.CacheMapper;
import net.todream.uni_translate.uni_translate.utils.TranslateCacheUtils;

@Component
@Slf4j
public class TranslateCacheConsumer {

    @Resource
    private TranslateCacheUtils translateCacheUtils;

    @Resource
    private CacheMapper cacheMapper;

    @KafkaListener(topics = "translate_return", groupId = "translate_return_group")
    public void translateWithReturn(TranslateWithReturnInDto in, Acknowledgment ack) {
        log.info("收到翻译返回对象: {}", in);
        String checkCode = translateCacheUtils.getMd5(in.getIn(), true);

        Cache data = new Cache();
        data.setCheckCode(checkCode);
        data.setFrom(in.getIn().getFrom().isEmpty() ? "auto" : in.getIn().getFrom());
        data.setTo(in.getIn().getTo());
        data.setPlatform(in.getIn().getPlatform());
        data.setText(in.getIn().getText());
        data.setResult(in.getOut());

        cacheMapper.insert(data);
        ack.acknowledge(); // 手动提交 offset
    }

}
