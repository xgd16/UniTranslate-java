package net.todream.uni_translate.uni_translate.service;

import java.util.List;

import net.todream.uni_translate.uni_translate.dto.TranslateClientInDto;
import net.todream.uni_translate.uni_translate.dto.TranslateClientOutDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;

public interface TranslateModeService {

    public void init(List<TranslateConf> conf);

    public String getModeName();

    public TranslateClientOutDto translate(TranslateClientInDto in);

}
