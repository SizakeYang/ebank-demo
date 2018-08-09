package com.sizake.ebank.db.dao;

import com.sizake.ebank.web.jsonObject.City;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface CityMapper {

    @Select("select * from world.city")
    @MapKey("id")
        //单一主键
    Map<String, City> getAll();//只能是Map<String/Integer/Object, City>,不能是Map<String/Integer/Object, Object>

}
