package com.sizake.ebank.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RequestController {
    //在Spring中，Controller的scope是singleton(单例)，
    // 也就是说在整个web系统中，只有一个TestController；但是其中注入的request却是线程安全的，原因在于：
    //使用这种方式，当Bean（本例的TestController）初始化时，Spring并没有注入一个request对象，而是注入了一个代理（proxy）；
    // 当Bean中需要使用request对象时，通过该代理获取request对象。
    // 也可以将其代码放入到了基类
    //当创建不同的派生类对象时，基类中的域（这里是注入的request）在不同的派生类对象中会占据不同的内存空间，也就是说将注入request的代码放在基类中对线程安全性没有任何影响；
    @Autowired
    private HttpServletRequest request1; //自动注入request

    //线程不安全
    //由于RequestController是单例的，request作为TestController的一个域，无法保证线程安全
    private HttpServletRequest request2;

    @ModelAttribute
    public void bindRequest(final HttpServletRequest request) {
        this.request2 = request;
    }

    @RequestMapping("/test1")
    public void test1(final HttpServletRequest request) { //request对象是方法参数，相当于局部变量，毫无疑问是线程安全的
        //do Somethings
    }


    @RequestMapping("/test2")
    public void test2() { //该方法与（自动注入）类似
        final HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        //do Somethings
    }

}
