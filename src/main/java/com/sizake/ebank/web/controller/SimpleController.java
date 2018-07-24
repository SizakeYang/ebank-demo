package com.sizake.ebank.web.controller;

import com.sizake.ebank.web.jsonObject.SimpleJson;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping(value = "/simple")
public class SimpleController {

    @GetMapping(value = {"/pathVariable/{id}/{parent}", "/pathVariable/{id}"})
    public String pathVariable(@PathVariable(value = "id") final String id,
                               @PathVariable(value = "parent", required = false) final String parent) {
        return String.format("id is %s, parent is %s", id, parent);
    }


    @GetMapping("/requestParam")
    public String requestParam(@RequestParam(value = "name", required = false, defaultValue = "default-name") final String name,
                               @RequestParam(value = "total", required = false, defaultValue = "-1") final Integer total,
                               @RequestParam(value = "money", required = false, defaultValue = "-1.11") final BigDecimal money,
                               @RequestParam(value = "date", required = false, defaultValue = "2018-09-08") @DateTimeFormat(pattern = "yyyy-MM-dd") final Date date) {
        return String.format("name is %s, total is %s, money is %s, date is %s", name, total, money, date);
    }

    @GetMapping("/requestHeader")
    public String requestHeader(@RequestHeader(value = "Accept-Language") final String encoding) {
        return String.format("Accept-Language is %s", encoding);
    }

    @GetMapping("/CookieValue")
    public String requestCookieValue(@CookieValue(value = "BAIDUID") final String baiduid) {
        return String.format("BAIDUID is %s", baiduid);
    }

    @PostMapping(value = "/requestBody", consumes = {"application/json"}, produces = {"application/json"})
    //consumes = "application/json":方法仅处理request Content-Type为“application/json”类型的请求
    //produces = "application/json":方法仅处理request请求中Accept头中包含了"application/json"的请求
    public String requestBody(@RequestBody final SimpleJson person)  {
        //return new ObjectMapper().writeValueAsString(person);
        return person.toString();
    }


    @RequestMapping(value = "/requestFromLegalWebSite", headers = "Referer=http://www.ifeng.com/*", params = "a=b")
    //headers = "Referer=http://www.ifeng.com/*": 仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求(支持通配符)
    //params = "a=b":仅处理GET请求中包含了名为“a”，值为“b”的请求
    public String requestFromLegalWebSite() {
        return "success";
    }



}
