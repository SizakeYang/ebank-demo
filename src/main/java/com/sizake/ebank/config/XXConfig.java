package com.sizake.ebank.config;


import lombok.Data;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "xx.test")
//@PropertySource注解不支持yml文件加载
@Data
public class XXConfig {

    private String hehe;

    private String[] txtarray;

    private List<Map<String, String>> listmap;

    private List<String> liststr;

    private Map<String, String> map;


    // 加载YML格式自定义配置文件
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
//        yaml.setResources(new FileSystemResource("config.yml"));//File引入
		yaml.setResources(new ClassPathResource("myConfig/xx.yaml"));//class引入
        configurer.setProperties(yaml.getObject());
        return configurer;
    }
}
