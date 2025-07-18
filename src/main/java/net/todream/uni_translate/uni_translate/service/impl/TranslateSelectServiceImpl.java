package net.todream.uni_translate.uni_translate.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.config.LanguageConfig;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;
import net.todream.uni_translate.uni_translate.service.TranslateSelectService;

@Service
public class TranslateSelectServiceImpl implements TranslateSelectService {

    @Resource
    private LanguageConfig languageConfig;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 选择翻译平台进行翻译
     * @param conf 翻译配置
     * @param in 输入
     * @return 翻译结果
     * @throws TranslateException
     */
    @Override
    public TranslateClientOutDto tanslate(TranslateConf conf, TranslateClientInDto in) {
        // 对请求语言Code进行对应转换
        String platform = in.getPlatform().toLowerCase();
        String formCode = in.getFrom();
        if (!formCode.isEmpty() && !formCode.equals("auto")) {
            formCode = languageConfig.getLang(platform, in.getFrom());
            in.setFrom(formCode);
        }
    
        String toCode = languageConfig.getLang(platform, in.getTo());
        in.setTo(toCode);
        // 选择对应的翻译进行操作
        StringBuilder modeBeanNameBuilder = new StringBuilder();
        modeBeanNameBuilder.append(platform).append("TranslateClient");
        String modeBeanName = modeBeanNameBuilder.toString();
        // 获取对应的翻译服务
        TranslateClientService translateMode;
        if (applicationContext.containsBean(modeBeanName)) {
            translateMode = applicationContext.getBean(modeBeanName, TranslateClientService.class);
        } else {
            translateMode = applicationContext.getBean("googleTranslateClient", TranslateClientService.class);
        }
        return translateMode.translate(conf, in);
    }

}
