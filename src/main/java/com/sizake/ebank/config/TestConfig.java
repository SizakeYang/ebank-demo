package com.sizake.ebank.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component //使用@Component是让该类能够在其他地方被依赖使用，即使用@Autowired注释来创建实例。
@ConfigurationProperties(prefix="web.xxx") //prefix：指定配置文件中键名称的前缀
@PropertySource(value={"classpath:/myConfig/test.properties"}) //指定配置文件的所在位置
@Data
public class TestConfig {

    private String msg;

    private String comment;
}
