package com.sizake.ebank.web.controller;

import com.sizake.ebank.web.jsonObject.SimpleJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/model")
public class ModelAttributeController {


    @ModelAttribute("motd")
    public String init01(@RequestParam final String motd) {
        return motd;
    }

    @ModelAttribute
    public void init02(Model model) {
        System.out.println("最先执行的方法02");
        model.addAttribute("number","19");
        model.addAttribute("json2",new SimpleJson("kafka",26));
    }

    @ModelAttribute("json1")//默认跟类名一致
    public SimpleJson init03() {
        System.out.println("最先执行的方法03");
        SimpleJson obj = new SimpleJson("sizake",21);
        return obj;
    }



    @RequestMapping(value = "/modelTest")
    public String modelTest(@ModelAttribute("json2") SimpleJson obj) { //合并对象到指定名称
        System.out.println("然后执行的方法");
        obj.setAge(33);
        return "Model";
    }

    @ModelAttribute("attributeName")
    @RequestMapping(value = "/modelTest2")
    public String modelTest2() {
        return "hi";//这个方法的返回值并不是表示一个视图名称,而是model属性的值,相当于在request中封装了key=attributeName，value=hi;此时视图名为{由RequestToViewNameTranslator根据请求"/model/modelTest2"转换为逻辑视图model/modelTest2。}
    }

    //已经定义过的模型方法（带有 @ModelAttribute 的方法）
    //HTTP Session 中和字段名匹配的会话方法（带有 @SessionAttribute 的方法，和模型方法类似，只是作用域不同）
    //经过 URL 转换器解析过的路径变量
    @RequestMapping(value = "/componies/{name}/departments/{age}/edit")
    public String modelTest3(@ModelAttribute SimpleJson obj) {
        System.out.println("然后执行的方法");
        return "Model";
    }

    //Spring is using Java Reflection for getting list of methods. In most cases it's quite unpredictable in terms of JDK.
    //ModelAttribute 标记的方法 执行顺序是不可预测的


}
