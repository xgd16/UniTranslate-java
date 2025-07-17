package net.todream.uni_translate.uni_translate.mapper;

import org.apache.ibatis.annotations.Mapper;

import net.todream.uni_translate.uni_translate.entity.Cache;

@Mapper
public interface CacheMapper {
    int insert(Cache record);
}
