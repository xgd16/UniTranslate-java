package net.todream.uni_translate.uni_translate.service;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;


public interface TranslateService {
    public TranslateClientOutDto translate (TranslateClientInDto in);
    
}
