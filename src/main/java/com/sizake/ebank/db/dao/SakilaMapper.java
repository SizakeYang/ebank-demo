package com.sizake.ebank.db.dao;

import com.sizake.ebank.web.jsonObject.Actor;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface SakilaMapper {

    /*
    自动映射甚至在特定的result map下也能工作。
    在这种情况下，对于每一个result map,所有的ResultSet提供的列，
    如果没有被手工映射，则将被自动映射。
    自动映射处理完毕后手工映射才会被处理。
    */

    @Results(id = "getActor", value = {
            @Result(property = "lastNameTest", column = "last_name")//其实也可以通过别名{select a as b from table}解决
    })
    @ConstructorArgs({ //构造器注入而非set注入,少用
            @Arg(column = "actor_id", name = "id", id = true),
            @Arg(column = "last_update", name = "last_update")//构造方法形参的名字(通过 @Param("xxx")指定)。从3.4.3版本开始，通过指定具体的名字，你可以以任意顺序写入arg元素
    })
    @Select("select a.actor_id,a.first_name,a.last_name,a.last_update from sakila.actor a")
    List<Actor> getActor();


    @Results(id = "getActor1", value = {
            @Result(property = "lastNameTest", column = "last_name")
    })
    @ConstructorArgs({ //构造器注入而非set注入,少用
            @Arg(column = "actor_id", id = true, javaType = String.class),
            @Arg(column = "last_update", javaType = Date.class)//需要指定顺序,跟对应的构造函数一致
    })
    @Select("select a.actor_id,a.first_name,a.last_name,a.last_update from sakila.actor a")
    List<Actor> getActor1();


}
