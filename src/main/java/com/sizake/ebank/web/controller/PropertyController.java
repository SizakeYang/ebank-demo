package com.sizake.ebank.web.controller;

import com.sizake.ebank.config.TestConfig;
import com.sizake.ebank.config.XXConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/property")
public class PropertyController {

    //@Value注解的优缺点正好相反，它只能一个个配置注入值；
    // 不支持数组、集合等复杂的数据类型；
    // 不支持数据校验；对属性名匹配有严格的要求。
    // 最大的特点是支持SpEL表达式，使其拥有更丰富的功能。

    @Value("${test.msg:default-msg}")
    private String msg;

    @Value("${test.comment:default-comment}")
    private String comment;

    @Value("${base.module.simpleList}")
    private String[] simpleList;


    @Autowired
    private XXConfig xxConfig;


    @Autowired
    private Environment env;


    @Autowired
    private TestConfig testConfig;


    @RequestMapping("/byValue")
    public String byValue() {
        return String.format("msg is %s,comment is %s ", this.msg, this.comment);
    }

    @RequestMapping("/byEnv")
    public String byEnv(@RequestParam("target") final String target) {
        return this.env.getProperty(target, "default value from env!");
    }

    @RequestMapping("/byBean")
    public String byBean() {
        return String.format("msg is %s,comment is %s", this.testConfig.getMsg(), this.testConfig.getComment());
    }

    @RequestMapping("/getSimpleList")
    public String getSimpleList() {
        return Arrays.toString(this.simpleList);
    }

    @RequestMapping("/getXXConfig")
    public String getXXConfig() {
        return this.xxConfig.toString();
    }


}
