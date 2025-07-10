package net.todream.uni_translate.uni_translate.service;

import java.util.List;

import net.todream.uni_translate.uni_translate.dto.TranslatePlatformAddPlatformDto;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformUpdatePlatformDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;

public interface TranslatePlatformService {

    public void addPlatform(TranslatePlatformAddPlatformDto data);

    public void updateById(TranslatePlatformUpdatePlatformDto data);

    public void deleteById(Integer id);

    public List<TranslateConf> getList();
}
