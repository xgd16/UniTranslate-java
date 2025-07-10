package net.todream.uni_translate.uni_translate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformAddPlatformDto;
import net.todream.uni_translate.uni_translate.dto.TranslatePlatformUpdatePlatformDto;
import net.todream.uni_translate.uni_translate.entity.TranslateConf;
import net.todream.uni_translate.uni_translate.mapper.TranslateConfMapper;
import net.todream.uni_translate.uni_translate.service.TranslatePlatformService;

@Service
public class TranslatePlatformServiceImpl implements TranslatePlatformService {

    @Resource
    private TranslateConfMapper translateConfMapper;

    @Override
    public void addPlatform(TranslatePlatformAddPlatformDto data) {
        TranslateConf entity = new TranslateConf();
        BeanUtils.copyProperties(data, entity);
        translateConfMapper.insert(entity);
    }

    @Override
    public void updateById(TranslatePlatformUpdatePlatformDto data) {
        TranslateConf entity = new TranslateConf();
        BeanUtils.copyProperties(data, entity);
        translateConfMapper.updateByPrimaryKey(entity);
    }

    @Override
    @Cacheable(
        value = "translateConfCache", 
        key = "'translatePlatformList'", 
        cacheManager = "longCacheManager",
        unless = "#result == null || #result.isEmpty()"
    )
    public List<TranslateConf> getList() {
        return translateConfMapper.selectAll();
    }

    @Override
    @CacheEvict(
        value = "translateConfCache", 
        key = "'translatePlatformList'"
    )
    public void deleteById(Integer id) {
        translateConfMapper.deleteByPrimaryKey(id);
    }

}
