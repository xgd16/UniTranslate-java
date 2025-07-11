package net.todream.uni_translate.uni_translate.service.impl;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.exception.TranslateException;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;
import net.todream.uni_translate.uni_translate.service.TranslateSelectService;

@Service
public class TranslateSelectServiceImpl implements TranslateSelectService {

    @Resource(name = "googleTranslateClient")
    private TranslateClientService googleClientService;

    /**
     * 选择翻译平台进行翻译
     * @param conf 翻译配置
     * @param in 输入
     * @return 翻译结果
     * @throws TranslateException
     */
    @Override
    public TranslateClientOutDto tanslate(TranslateConf conf, TranslateClientInDto in) {
        switch (in.getPlatform()) {
            case "google":
            default:
                return googleClientService.translate(conf, in);
        }
    }

}
