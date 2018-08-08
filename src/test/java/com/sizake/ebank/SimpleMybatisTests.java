package com.sizake.ebank;

import com.sizake.ebank.db.dao.LanguageMapper;
import com.sizake.ebank.web.jsonObject.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleMybatisTests {

    @Autowired
    private LanguageMapper languageMapper;

    /*

        2018-08-08 16:48:48.670 DEBUG 11072 --- [           main] org.mybatis.spring.SqlSessionUtils       : Creating a new SqlSession
        2018-08-08 16:48:48.670 DEBUG 11072 --- [           main] org.mybatis.spring.SqlSessionUtils       : SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3b98b809] was not registered for synchronization because synchronization is not active
        2018-08-08 16:48:48.670 DEBUG 11072 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Fetching JDBC Connection from DataSource
        2018-08-08 16:48:48.670 DEBUG 11072 --- [           main] o.m.s.t.SpringManagedTransaction         : JDBC Connection [HikariProxyConnection@200760156 wrapping com.mysql.cj.jdbc.ConnectionImpl@6ff6efdc] will not be managed by Spring
        2018-08-08 16:48:48.670 DEBUG 11072 --- [           main] c.s.e.d.d.L.findByObjAndReturnObj        : ==>  Preparing: SELECT * FROM world.countrylanguage l WHERE l.CountryCode = ? and l.language = ?
        2018-08-08 16:48:48.670 DEBUG 11072 --- [           main] c.s.e.d.d.L.findByObjAndReturnObj        : ==> Parameters: ABW(String), Dutch(String)
        2018-08-08 16:48:48.671 DEBUG 11072 --- [           main] c.s.e.d.d.L.findByObjAndReturnObj        : <==      Total: 1
        2018-08-08 16:48:48.672 DEBUG 11072 --- [           main] org.mybatis.spring.SqlSessionUtils       : Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3b98b809]
        2018-08-08 16:48:48.672 DEBUG 11072 --- [           main] o.s.jdbc.datasource.DataSourceUtils      : Returning JDBC Connection to DataSource


        这里执行了2次sql查询,看似我们使用了同一个sqlSession,
        但是实际上因为我们的dao继承了SqlSessionDaoSupport,
        而SqlSessionDaoSupport内部sqlSession的实现是使用用动态代理实现的,
        这个动态代理sqlSessionProxy使用一个模板方法封装了select()等操作,每一次select()查询都会自动先执行openSession(),执行完close()以后调用close()方法,
        相当于生成了一个新的session实例,所以我们无需手动的去关闭这个session()(关于这一点见下面mybatis的官方文档),
        当然也无法使用mybatis的一级缓存,也就是说mybatis的一级缓存在spring中是没有作用的.

        But when using MyBatis-Spring your beans will be injected with a Spring managed SqlSession or a Spring managed mapper.
        That means that Spring will always handle your transactions.
        You cannot call SqlSession.commit(), SqlSession.rollback() or SqlSession.close() over a Spring managed SqlSession.
        If you try to do so, a UnsupportedOperationException exception will be thrown.
        Note these methods are not exposed in injected mapper classes.
     */

    @Test
    public void annontiation() {
        System.out.println(String.format("list is %s ",this.languageMapper.findByStringAndReturnList("ABW")));

        Map<String,Object> conditions = new HashMap<>();
        conditions.put("country","ABW");
        conditions.put("language","Dutch");
        System.out.println(String.format("map is %s ",this.languageMapper.findByMapAndReturnMap(conditions)));

        Language language = new Language();
        language.setCountryCode("ABW");
        language.setLanguage("Dutch");

        System.out.println(String.format("obj is %s ",this.languageMapper.findByObjAndReturnObj(language)));

    }
}
