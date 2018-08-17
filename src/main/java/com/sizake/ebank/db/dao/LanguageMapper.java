package com.sizake.ebank.db.dao;

import com.sizake.ebank.web.jsonObject.Language;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

//@Mapper
public interface LanguageMapper extends EbankMapper {

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country}")
        //#{ } 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符。
    List<Language> findByStringAndReturnList(@Param("country") String country);

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} and l.language = #{language}")
    Map<String, String> findByMapAndReturnMap(Map<String, Object> paramMap);//只能返回一条数据;

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} ")
    List<Map<String, String>> findByMapAndReturnListMap1(Map<String, Object> paramMap);//但 country 无值时,查询为 SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = ‘null’

    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{countryCode} and l.language = #{language}")
    Language findByObjAndReturnObj(Language l);


    @Select({"<script>",
            "SELECT * FROM world.countrylanguage l",
            "WHERE 1=1",
            "<when test='title!=null'>",
            "AND l.CountryCode  = #{country}",
            "</when>",
            "</script>"})
    List<Map<String, String>> findByScript(Map<String, Object> paramMap);

    // find with like
    @Select({
            "SELECT * FROM world.countrylanguage l",
            "WHERE 1=1",
            "and l.CountryCode like concat('%',#{countryCode},'%')"
    })
    List<Map<String, String>> findWithLike(Map<String, Object> paramMap);


    @Select({"<script>",
            "   SELECT * FROM world.countrylanguage l",
            "       <where>",
            //"<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">",
            "           <choose>",
            "               <when test=\"country != null\">",
            "                   AND l.countryCode = #{country}",
            "               </when>",
            "               <when test=\"language != null\">",
            "                   AND l.language = #{language}",
            "               </when>",
            "               <otherwise>",
            "                   AND l.IsOfficial = 'T' ",
            "               </otherwise>",
            "           </choose>",
            "       </where>",
            //"</trim>",
            "</script>"})
    List<Map<String, String>> findByScriptWithChooseAndPre(Map<String, Object> paramMap);


    @Select({
            " SELECT * FROM world.countrylanguage l where l.countryCode in ",
            "        <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\"> ",
            "            #{item} ",
            "        </foreach> "
    })
    List<Map<String, String>> findByScriptWithFor(List<String> countrys);


}
