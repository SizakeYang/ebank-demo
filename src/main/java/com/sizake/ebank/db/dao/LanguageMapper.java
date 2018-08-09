package com.sizake.ebank.db.dao;

import com.sizake.ebank.web.jsonObject.Language;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LanguageMapper {

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country}")
        //#{ } 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符。
    List<Language> findByStringAndReturnList(@Param("country") String country);

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} and l.language = #{language}")
    Map<String, String> findByMapAndReturnMap(Map<String, Object> paramMap);//只能返回一条数据;

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} ")
    List<Map<String, String>> findByMapAndReturnListMap1(Map<String, Object> paramMap);

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} ")
    @MapKey("CountryCode")
    Map<String,Map<String, String>> findByMapAndReturnMultiMap1(Map<String, Object> paramMap);


    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{countryCode} and l.language = #{language}")
    Language findByObjAndReturnObj(Language l);

}
