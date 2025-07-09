package net.todream.uni_translate.uni_translate.service;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;

public interface TranslateClientService {
    public TranslateClientOutDto translate(TranslateConf conf,TranslateClientInDto in);

}
