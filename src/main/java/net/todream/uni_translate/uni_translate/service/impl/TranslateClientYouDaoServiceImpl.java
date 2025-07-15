package net.todream.uni_translate.uni_translate.service.impl;

import org.springframework.stereotype.Service;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.service.TranslateClientService;


@Service("youdaoTranslateClient")
public class TranslateClientYouDaoServiceImpl implements TranslateClientService {

    @Override
    public TranslateClientOutDto translate(TranslateConf conf, TranslateClientInDto in) {

        return null;
    }

}
