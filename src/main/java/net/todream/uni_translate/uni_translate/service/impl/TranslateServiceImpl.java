package net.todream.uni_translate.uni_translate.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.mapper.TranslateConfMapper;
import net.todream.uni_translate.uni_translate.service.TranslateModeService;
import net.todream.uni_translate.uni_translate.service.TranslatePlatformService;
import net.todream.uni_translate.uni_translate.service.TranslateService;

@Service
public class TranslateServiceImpl implements TranslateService {

    @Resource
    private TranslateConfMapper translateConfMapper;

    private static final Logger logger = LoggerFactory.getLogger(TranslateServiceImpl.class);

    @Resource
    private TranslatePlatformService translatePlatformService;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    @Cacheable(
        value = "translateCache", 
        keyGenerator = "translateMd5PlatformCacheGen",
        unless = "#result == null"
    )
    public TranslateClientOutDto translate(TranslateClientInDto in) {
        // 获取配置
        List<TranslateConf> confList = getConfigList(in.getPlatform(), true);
        // 调用指定的翻译模式
        if (!applicationContext.containsBean(in.getMode())) {
            throw new TranslateException("翻译模式不存在: " + in.getMode());
        }
        TranslateModeService mode = applicationContext.getBean(in.getMode(), TranslateModeService.class);
        // 初始化
        mode.init(confList);
        // 执行翻译
        return mode.translate(in);
    }

    /**
     * 获取配置列表
     * @param platform 平台名称
     * @param isFallbackEnabled 是否启用替代配置
     * @return 配置列表
     */
    @Override
    public List<TranslateConf> getConfigList(String platform, Boolean isFallbackEnabled) {
        // 获取所有配置数据
        List<TranslateConf> confList = translatePlatformService.getList();
        // 判断是否只获取指定平台的配置
        if (!platform.isEmpty() && !isFallbackEnabled) {
            confList = confList.stream()
                .filter(conf -> conf.getPlatform().equals(platform))
                .toList();
        }
        // 判断是否启用替代配置
        if (!platform.isEmpty() && isFallbackEnabled) {
            confList = confList.stream()
                .sorted((conf1, conf2) -> {
                    if (conf1.getPlatform().equals(platform) && !conf2.getPlatform().equals(platform)) {
                        return -1;
                    } else if (!conf1.getPlatform().equals(platform) && conf2.getPlatform().equals(platform)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }).toList();
        }
        return confList;
    }

}
