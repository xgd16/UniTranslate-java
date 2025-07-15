
package net.todream.uni_translate.uni_translate.mapper;

import org.apache.ibatis.annotations.Mapper;

import net.todream.uni_translate.uni_translate.entity.TranslateConf;

import java.util.List;

@Mapper
public interface TranslateConfMapper {
    int insert(TranslateConf record);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(TranslateConf record);
    TranslateConf selectByPrimaryKey(Integer id);
    List<TranslateConf> selectAll();
}