package com.sizake.ebank.web.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@RestController
@RequestMapping("/session")
@SessionAttributes(value = {"book", "description"}, types = {Double.class})
// 前提:Session 已经存在,否则执行如下方法会有异常: {Cannot create a session after the response has been committed}
//错误原因:在Response响应(response.getOutputStream())之后，才创建Session(request.getSession())。而此时已经找不到Session ID，所以会报错。
//解决办法:在Response响应(response.getOutputStream())之前进行Session创建(request.getSession())。
public class SimpleSessionController {

    @RequestMapping("/index")
    public String index(final Model model) {
        model.addAttribute("book", "金刚经");
        model.addAttribute("description", "不擦擦擦擦擦擦擦车");
        model.addAttribute("price", new Double("1000.00"));
        //跳转之前将数据保存到book、description和price中，因为注解@SessionAttribute中有这几个参数
        return "set done!";
    }

    @RequestMapping("/get")
    public String get(@ModelAttribute("book") final String book, final ModelMap model,
                      final SessionStatus sessionStatus) {
        //可以获得book、description和price的参数
        System.out.println(model.get("book") + ";" + model.get("description") + ";" + model.get("price"));
        sessionStatus.setComplete();
        return "set done!";
    }

    @RequestMapping("/complete")
    public String complete(final ModelMap modelMap) {
        //已经被清除，无法获取book的值
        System.out.println(modelMap.get("book"));
        modelMap.addAttribute("book", "妹纸");
        return "check done!";
    }

}
