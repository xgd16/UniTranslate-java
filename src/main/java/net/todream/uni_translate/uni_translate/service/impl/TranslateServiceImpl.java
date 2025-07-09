package net.todream.uni_translate.uni_translate.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.mapper.TranslateConfMapper;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;
import net.todream.uni_translate.uni_translate.service.TranslateService;

@Service
public class TranslateServiceImpl implements TranslateService {

    @Resource
    private TranslateConfMapper translateConfMapper;

    @Resource(name = "googleTranslateClient")
    private TranslateClientService googleClientService;

    @Override
    @Cacheable(value = "translateCache", keyGenerator = "translateMd5PlatformCacheGen")
    public TranslateClientOutDto translate(TranslateClientInDto in) {

        List<TranslateConf> confList = translateConfMapper.selectAll();

        for (TranslateConf translateConf : confList) {
            try {
                return selectTranslate(translateConf, in);
            } catch (Exception e) {
                System.err.println("Error processing configuration: " + translateConf.getId() + ", " + e.getMessage());
                continue;
            }
        }

        return null;
    }

    @Override
    public TranslateClientOutDto selectTranslate(TranslateConf conf, TranslateClientInDto in) {
        switch (in.getPlatform()) {
            case "google":
                return googleClientService.translate(conf, in);
            default:
                return googleClientService.translate(conf, in);
        }
    }

}
