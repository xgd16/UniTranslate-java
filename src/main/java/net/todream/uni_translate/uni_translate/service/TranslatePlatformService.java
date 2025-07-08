package net.todream.uni_translate.uni_translate.service;

import net.todream.uni_translate.uni_translate.dto.TranslatePlatformAddPlatformDto;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformUpdatePlatformDto;

public interface TranslatePlatformService {

    public void addPlatform(TranslatePlatformAddPlatformDto data);

    public void updateById(TranslatePlatformUpdatePlatformDto data);
}
