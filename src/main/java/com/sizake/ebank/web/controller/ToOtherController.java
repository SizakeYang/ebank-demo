package com.sizake.ebank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/toOther")
public class ToOtherController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/login1")
    public String login1() {
        return "redirect:login";
    }

    @GetMapping(value = "/login2")
    public String login2() {
        return "forward:login";
    }

    //forward和redirect区别。
    // forward是服务器转发，浏览器地址不变，服务器将请求的新地址内容返回给原地址。
    // redirect是浏览器重定向，浏览器地址改变，服务器告诉浏览器要访问新的地址后浏览器重新访问新地址。
}
