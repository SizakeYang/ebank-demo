package com.sizake.ebank.config;

import com.sizake.ebank.db.dao.EbankMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 有时候我们指定的基包下面的并不全是我们定义的Mapper接口，为此@MapperScan还为我们提供了另外两个可以缩小搜索和注册范围的属性。
// 一个是annotationClass，另一个是markerInterface。
// annotationClass：当指定了annotationClass的时候，MapperScannerConfigurer将只注册使用了annotationClass注解标记的接口。-->{default:Annotation.class,即有任意一种注解即可}
// markerInterface：markerInterface是用于指定一个接口的，当指定了markerInterface之后，MapperScannerConfigurer将只注册继承自markerInterface的接口。
// 如果上述两个属性都指定了的话，那么MapperScannerConfigurer将取它们的{并集，而不是交集}。即使用了annotationClass进行标记或者继承自markerInterface的接口都将被注册为一个MapperFactoryBean。
@MapperScan(basePackages = {"com.sizake.ebank.db"}, annotationClass = Mapper.class, markerInterface = EbankMapper.class, sqlSessionTemplateRef = "ebankSqlSessionTemplate0")
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
        bean.setEnvironment("ebankData0");
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new ClassPathResource("/dbConfig/mybatis_ebank0_global_config.xml"));
        return bean.getObject();
    }

    @Bean(name = "ebankTransactionManager0")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("ebankDataSource0") final DataSource dataSource) {
        //在单一的JDBC/Mybatis DataSource中管理事务;对于涉及到多数据源操作的服务无法保证事务!!!
        //注意,为事务管理器指定的 DataSource 必须和用来创建 SqlSessionFactoryBean 的 是同一个数据源,否则事务管理器就无法工作了。
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ebankSqlSessionTemplate0")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ebankSqlSessionFactory0") final SqlSessionFactory sqlSessionFactory) {
        //SqlSessionTemplate 是线程安全的, 可以被多个 DAO 所共享使用。
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
        // reuse:
        // 它重用的是Statement对象，它会在内部利用一个Map把创建的Statement都缓存起来，
        // 每次在执行一条SQL语句时，它都会去判断之前是否存在基于该SQL缓存的Statement对象，
        // 存在而且之前缓存的Statement对象对应的Connection还没有关闭的时候就继续用之前的Statement对象，
        // 否则将创建一个新的Statement对象，并将其缓存起来。
        // 因为Executor对象随着SqlSession的创建而创建，被保存在SqlSession对象中，因此Executor的生命周期与SqlSession一致。
        // 所以我们缓存在ReuseExecutor上的Statement的作用域是同一个SqlSession
        // -->关于PreparedStatementCache内存问题,参考hikarcp

        // batch:
        // BatchExecutor的doUpdate更新操作是批量执行，
        // 每一次更新操作保存在内部的statementList中，
        // 每调用一次flushStatements()进行一次批量执行。
        // commit时会调用flushStatements()，
        // 查询操作时也会调用flushStatements()。
        // 注:BatchExecutor 是没有做 PSCache
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

        But when using MyBatis-Spring your beans will be injected with a Spring managed SqlSession or a Spring managed mapper.
        That means that Spring will always handle your transactions.
        You cannot call SqlSession.commit(), SqlSession.rollback() or SqlSession.close() over a Spring managed SqlSession.
        If you try to do so, a UnsupportedOperationException exception will be thrown.
        Note these methods are not exposed in injected mapper classes.

        mybatis-spring中的sqlsession通过spring去管理，
        前面说到mybatis的一级缓存生效的范围是sqlsession，是为了在sqlsession没有关闭时，业务需要重复查询相同数据使用的。一旦sqlsession关闭，则由这个sqlsession缓存的数据将会被清空。
        spring对mybatis的sqlsession的使用是由template控制的，sqlsession又被spring当作resource放在当前线程的上下文里（threadlocal),spring通过mybatis调用数据库的过程如下：
        1、需要访问数据
        2、spring检查到了这种需求，于是去申请一个mybatis的sqlsession，并将申请到的sqlsession与当前线程绑定，放入threadlocal里面
        3、template从threadlocal获取到sqlsession，去执行查询
        4、查询结束，清空threadlocal中与当前线程绑定的sqlsession，释放资源
        5、又需要访问数据
        6、返回到步骤2

        结论：通过以上步骤后发现，同一线程里面两次查询同一数据所使用的sqlsession是不相同的，
        所以mybatis结合spring后，mybatis的一级缓存失效了。

     */

}