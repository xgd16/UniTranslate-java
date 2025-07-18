package net.todream.uni_translate.uni_translate.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.todream.uni_translate.uni_translate.entity.Cache;

@Mapper
public interface CacheMapper {

    int insert(Cache record);

    Optional<String> getCacheContent(@Param("checkCode") String checkCode);

}
