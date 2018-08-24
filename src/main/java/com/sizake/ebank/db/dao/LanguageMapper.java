//package com.sizake.ebank.db.dao;
//
//import com.sizake.ebank.web.jsonObject.Language;
//import com.sizake.ebank.web.jsonObject.Languages;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//import java.util.Map;
//
////@Mapper
//public interface LanguageMapper extends EbankMapper {
//
//    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country}")
//        //#{ } 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符。
//    List<Language> findByStringAndReturnList(@Param("country") String country);
//
//    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} and l.language = #{language}")
//    Map<String, String> findByMapAndReturnMap(Map<String, Object> paramMap);//只能返回一条数据;
//
//    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{country} ")
//    List<Map<String, String>> findByMapAndReturnListMap1(Map<String, Object> paramMap);//但 country 无值时,查询为 SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = ‘null’
//
//    //默认resultMap-id:
//    //@ResultMap("com.sizake.ebank.db.dao.LanguageMapper{全限定类名}.findByObjAndReturnObj{方法名}-Language{方法参数非全限定名拼接,以‘-’为分割符,如无入参则为void}")
//    //@ResultMap("com.sizake.ebank.db.dao.LanguageMapper.findByObjAndReturnObj-Language")
//    //详情可见:Configuration.resultMaps--> MapperAnnotationBuilder.generateResultMapName
//    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{countryCode} and l.language = #{language}")
//    Language findByObjAndReturnObj(Language l);
//
//    @Select("SELECT * FROM world.countrylanguage l WHERE l.CountryCode  = #{countryCode} and l.language = #{language}")
//    Language findByObjAndReturnObj1(Language l);
//
//
//    @Select({"<script>",
//            "SELECT * FROM world.countrylanguage l",
//            "WHERE 1=1",
//            "<when test='title!=null'>",
//            "AND l.CountryCode  = #{country}",
//            "</when>",
//            "</script>"})
//    List<Map<String, String>> findByScript(Map<String, Object> paramMap);
//
//    // find with like
//    @Select({
//            "SELECT * FROM world.countrylanguage l",
//            "WHERE 1=1",
//            "and l.CountryCode like concat('%',#{countryCode},'%')"
//    })
//    List<Map<String, String>> findWithLike(Map<String, Object> paramMap);
//
//
//    @Select({"<script>",
//            "   SELECT * FROM world.countrylanguage l",
//            "       <where>",
//            //"<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">",
//            "           <choose>",
//            "               <when test=\"country != null\">",
//            "                   AND l.countryCode = #{country}",
//            "               </when>",
//            "               <when test=\"language != null\">",
//            "                   AND l.language = #{language}",
//            "               </when>",
//            "               <otherwise>",
//            "                   AND l.IsOfficial = 'T' ",
//            "               </otherwise>",
//            "           </choose>",
//            "       </where>",
//            //"</trim>",
//            "</script>"})
//    List<Map<String, String>> findByScriptWithChooseAndPre(Map<String, Object> paramMap);
//
//    //你可以将一个 List 实例或者数组作为参数对象传给 MyBatis，当你这么做的时候，MyBatis 会自动将它包装在一个 Map 中并以名称为键。List 实例将会以“list”作为键，而数组实例的键将是“array”
//    //0.foreach-list<基本类型>
//    @Select({"<script>",
//            " SELECT * FROM world.countrylanguage l where l.countryCode in ",
//            "        <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\"> ",
//            "            #{item} ",
//            "        </foreach> ",
//            "</script>"
//    })
//    List<Map<String, String>> findByScriptWithFor(List<String> countrys);//可以有某一项为null:list.add(null);
//
//    //1.foreach-list<对象>
//    @Select({"<script>",
//            " SELECT * FROM world.countrylanguage l where l.countryCode in ",
//            "        <foreach collection=\"list\" index=\"index\" item=\"language\" open=\"(\" separator=\",\" close=\")\"> ",
//            "            #{language.countryCode} ",
//            "        </foreach> ",
//            "</script>"
//    })
//    List<Map<String, String>> findByScriptWithFor1(List<Language> languages);
//
//
//    //2.foreach-map-传入map的某一项
//    @Select({"<script>",
//            " SELECT * FROM world.countrylanguage l where l.countryCode in ",
//            "        <foreach collection=\"countryCodes\" index=\"index\" item=\"language\" open=\"(\" separator=\",\" close=\")\"> ",
//            "            #{language.countryCode} ",
//            "        </foreach> ",
//            "</script>"
//    })
//    List<Map<String, String>> findByScriptWithFor2(Map<String, Object> obj);
//
//    //3.foreach-对象里的list
//    @Select({"<script>",
//            " SELECT * FROM world.countrylanguage l where l.countryCode in ",
//            "        <foreach collection=\"languages\" index=\"index\" item=\"language\" open=\"(\" separator=\",\" close=\")\"> ",
//            "            #{language.countryCode,jdbcType=DOUBLE} ",//1:jdbcTyp 必须符合 org.apache.ibatis.type.JdbcType;2 jdbcType 影响typeHandle的选择,但不是决定性作用:a.TypeHandlerRegistry:于构造函数 预先注册好跟对应javatype对应的typehandler(以及默认的);2.TypeHandlerRegistry.getTypeHandler 在根据javatype和jdbctype获取TypeHandler,如果无法根据javatype获取,报异常;如果无法根据jdbctype获取(没有设置或者设置找不到),选择默认(同时没有任何日志!!!)
//            "        </foreach> ",
//            "</script>"
//    })
//    List<Map<String, String>> findByScriptWithFor3(Languages obj);
//
//    //4.foreach-遍历map
//    @Select({"<script>",
//            " SELECT * FROM world.countrylanguage l where ",
//            " <foreach item=\"item\" index=\"key\" collection=\"obj\" separator=\"AND\">",
//            "            ${key} = #{item} ",//对于诸如表名、字段名（如order by子句后的排序字段）这些表本身或其字段的名字，和SQL关键字（如order by子句后的asc关键字），是不能使用#{…}方式的，而只能使用字符串替换的${…}方式
//            "        </foreach> ",
//            "</script>"
//    })
//    List<Map<String, String>> findByScriptWithFor4(@Param("obj") Map<String, Object> obj);//可以有某一项为null:map.put("countryCode", null);
//
//
//}
