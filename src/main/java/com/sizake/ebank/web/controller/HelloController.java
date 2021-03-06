package com.sizake.ebank.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //等价于@Controller+@ResponseBody
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

//    @RequestMapping()  //-->http://localhost:8080/ebank/xxxx -->and other any unmapping url --> why:the details can be seen in the DispatcherServlet.getHandler
//    public String test0(){
//        return "no mapping";
//    }

    @RequestMapping("/") //http://localhost:8080/ebank/
    public String test1() {
        return "to the ebank root";
    }

}
