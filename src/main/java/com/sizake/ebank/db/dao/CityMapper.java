package com.sizake.ebank.db.dao;

import com.sizake.ebank.web.jsonObject.City;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
//bean name:cityMapper
public interface CityMapper {

    @Select("select * from world.city")
    @MapKey("id")
//单一主键;小写有区别,此处只能用"id",即与对象City.id 一致
    Map<String, City> getAllAndReturnMapObj();


    @Select("select id,name,countryCode,district,population from world.city")
    @MapKey("id")
//单一主键;大小写有区别,此处只能用"id",即与查询语句 select id ...... 一致
    Map<String, Map<String, String>> getAllAndReturnListMap();

    //联合主键-->way1:get List<Map> and transform it to Map


    //Parameters: 0(Integer), 1(Integer)
    //index 于list/array:序号,从0开始计数
    @Select({"<script>",
            " SELECT * FROM world.city c where c.id in ",
            "        <foreach collection=\"list\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\"> ",
            "            #{index} ",
            "        </foreach> ",
            "</script>"
    })
    List<Map<String, String>> findByScriptWithFor(List<String> countrys);


//    @Select("select * from world.city")
//        //在mybatis-spring的整合中，mybatis中sqlSession中由org.mybatis.spring.SqlSessionTemplate实现替代。
//        // sqlSession关闭是由SqlSessionTemplate管理,所以返回后的Cursor对象是已经被关闭了的(Cursor.isOpen)，无法使用。
//    Cursor<City> getAllAndReturnMapObjWithCursor();//可以通过分页解决

}
