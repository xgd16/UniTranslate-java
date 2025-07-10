package net.todream.uni_translate.uni_translate.service;

import java.util.List;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;


public interface TranslateService {
    public TranslateClientOutDto translate (TranslateClientInDto in);
    public TranslateClientOutDto selectTranslate(TranslateConf conf, TranslateClientInDto in);
    public List<TranslateConf> getConfigList(String platform, Boolean isFallbackEnabled);
    
}
