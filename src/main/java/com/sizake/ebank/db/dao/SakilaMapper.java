package com.sizake.ebank.db.dao;

import com.sizake.ebank.web.jsonObject.Actor;
import com.sizake.ebank.web.jsonObject.Film;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface SakilaMapper {

    /*
    自动映射甚至在特定的result map下也能工作。
    在这种情况下，对于每一个result map,所有的ResultSet提供的列，
    如果没有被手工映射，则将被自动映射。
    自动映射处理完毕后手工映射才会被处理。
    */

    @Results(id = "getActor", value = {
            @Result(property = "lastNameTest", column = "last_name", id = false)
    })
    @ConstructorArgs({ //构造器注入而非set注入,少用
            @Arg(column = "actor_id", name = "id", id = true),//flagging results as ID will help improve overall performance
            @Arg(column = "last_update", name = "last_update")//构造方法形参的名字(通过 @Param("xxx")指定)。从3.4.3版本开始，通过指定具体的名字，你可以以任意顺序写入arg元素
    })
    //其实也可以通过别名{select a as b from table}解决
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


    @Results(id = "getFilm", value = {
            //flagging results as ID will help improve overall performance
            // 实际上可能没有用处
            // 应为目前{3.4.6}版本缺少类似于collection这样的处理一对多映射的注解,且
            //根据 MapperAnnotationBuilder.applyResults/apperAnnotationBuilder.applyConstructorArgs,在不引入外部resultMap的情况下 MapperBuilderAssistant.buildResultMapping的
            @Result(property = "id", column = "film_id", id = true),
            @Result(property = "cost", column = "replacement_cost"),
            @Result(property = "category.name", column = "name")//无论是哪一种情形，你都可以使用通常的点式分隔形式进行复杂属性导航
    })
    @Select("select f.film_id,f.title,f.replacement_cost,c.name  from sakila.film f\n" +
            "left join sakila.film_category fc on f.film_id = fc.film_id\n" +
            "left join sakila.category c on c.category_id = fc.category_id")
    List<Film> getFilm();


    @ResultMap("filmForTest")//等同于@ResultMap("com.sizake.ebank.db.dao.SakilaMapper.filmForTest")
    @Select("select f.film_id,f.title,f.replacement_cost,c.name  from sakila.film f\n" +
            "left join sakila.film_category fc on f.film_id = fc.film_id\n" +
            "left join sakila.category c on c.category_id = fc.category_id")
    List<Film> getFilm1();


    @ResultMap("filmForTest")//等同于@ResultMap("com.sizake.ebank.db.dao.SakilaMapper.filmForTest")
    //@ResultMap("com.sizake.ebank.db.dao.SakilaMapper.filmForTest")
    @Select("select f.film_id,f.title,f.replacement_cost,c.category_id, c.name,a.actor_id,a.first_name,a.last_name,a.last_update  from sakila.film f\n" +
            "left join sakila.film_category fc on f.film_id = fc.film_id\n" +
            "left join sakila.category c on c.category_id = fc.category_id\n" +
            "left join sakila.film_actor fa on fa.film_id = f.film_id\n" +
            "left join sakila.actor a on a.actor_id = fa.actor_id\n" +
            "")
    List<Film> getFilm2();//一对一,一对多


    @ResultMap("filmForTest2")
    @Select("select f.film_id,f.title,f.replacement_cost,\n" +
            "       a.actor_id,a.first_name,a.last_name,a.last_update,\n" +
            "       f2.film_id as actor_film_id,f2.title as actor_title,f2.replacement_cost as actor_replacement_cost\n" +
            "from sakila.film f\n" +
            "left join sakila.film_actor fa on fa.film_id = f.film_id\n" +
            "left join sakila.actor a on a.actor_id = fa.actor_id\n" +
            "left join sakila.film_actor fa2  on fa2.actor_id = a.actor_id\n" +
            "left join sakila.film f2 on f2.film_id = fa2.film_id\n" +
            "where f.film_id = 1\n")
    List<Film> getFilm3();//多对多


    @ResultMap("filmForTest3")
    //@ResultMap("filmForTest5")
    @Select("select f.film_id,f.title,f.replacement_cost,f.rental_rate,f.rental_duration from sakila.film f\n" +
            "where f.rental_duration in(4)\n" +
            "and f.film_id in (1,4,13)")
    List<Film> getFilm4();


    @Select("select * from sakila.film f where f.film_id<20 ")
    List<Map<String, String>> getFilm5();


    @Select({"<script>",
            "select * from sakila.film f",
            "WHERE",
            "<if test='title!=null'>",//test采用ongl解析
            " <![CDATA[f.film_id<20]]>  ",//转义 or cdat
            "</if>",//if-->when,is ok.
            "</script>"})
    List<Map<String, String>> getFilm6(Map<String, Object> paramMap);



}
