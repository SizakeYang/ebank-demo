package com.sizake.ebank.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/router")
public class RouterController {

    @RequestMapping //-->http://localhost:8080/ebank/router
    public String router1() {
        return "router without /";
    }

    @RequestMapping("/")  //-->http://localhost:8080/ebank/router/
    public String router2() {
        return "router with /";
    }

    @RequestMapping(value = "/ant1?")
    public String ant1() {
        return "ant1?";
    }

    @RequestMapping(value="/ant2*")
    //localhost:8080/ebank/router/ant22 或者
    //localhost:8080/ebank/router/ant2a
    public String ant2(){
        return "ant2*";
    }

    @RequestMapping(value="/ant3/*")
    //localhost:8080/ebank/router/ant3/aaaa 或者
    //localhost:8080/ebank/router/ant3/123
    public String ant3(){
        return "ant3/*";
    }

    @RequestMapping(value="/ant4/**")
    //localhost:8080/ebank/router/ant4/ 或者
    //localhost:8080/ebank/router/ant4/aaa 或者
    //localhost:8080/ebank/router/ant4/aaa/123
    public String ant4(){
        return "ant4/**";
    }

    //正则表达式
    @RequestMapping(value="/student/{name:\\w+}-{age:\\d+}")
    //localhost:8080/ebank/router/student/wangwu-33 或者
    //localhost:8080/ebank/router/student/zhao4-22
    public String regUrl(@PathVariable String name, @PathVariable int age){
        return "name:"+name+" age:"+age;
    }


}
