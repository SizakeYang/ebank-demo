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

}