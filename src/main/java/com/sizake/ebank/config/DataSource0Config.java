package com.sizake.ebank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.sizake.ebank.db"}, sqlSessionTemplateRef = "ebankSqlSessionTemplate0")
public class DataSource0Config {

    //@Autowired可以对成员变量、方法和构造函数进行标注，来完成自动装配的工作，默认根据【类型】进行自动装配，如果需要按【名称】进行装配，则需要配合@Qualifier使用。

    @Bean(name = "ebankDataSource0")
    @Primary
    public DataSource dataSource() {
        final HikariConfig config = new HikariConfig("/dbConfig/hikari-dataSource0.properties");
        return new HikariDataSource(config);
    }

    @Bean(name = "ebankSqlSessionFactory0")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ebankDataSource0") final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "ebankTransactionManager0")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("ebankDataSource0") final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ebankSqlSessionTemplate0")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ebankSqlSessionFactory0") final SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


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
        而SqlSessionTemplate内部sqlSession的实现是使用用动态代理实现的,
        这个动态代理sqlSessionProxy使用一个模板方法封装了select()等操作,每一次select()查询都会自动先执行openSession(),执行完close()以后调用close()方法,
        相当于生成了一个新的session实例,所以我们无需手动的去关闭这个session()(关于这一点见下面mybatis的官方文档),
        当然也无法使用mybatis的一级缓存,也就是说mybatis的一级缓存在spring中是没有作用的.

        But when using MyBatis-Spring your beans will be injected with a Spring managed SqlSession or a Spring managed mapper.
        That means that Spring will always handle your transactions.
        You cannot call SqlSession.commit(), SqlSession.rollback() or SqlSession.close() over a Spring managed SqlSession.
        If you try to do so, a UnsupportedOperationException exception will be thrown.
        Note these methods are not exposed in injected mapper classes.
     */

}