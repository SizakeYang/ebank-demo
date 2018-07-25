package com.sizake.ebank.web.controller;


import com.sizake.ebank.web.jsonObject.SimpleJson;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/complex")
public class ComplexController {

    @RequestMapping("/baseTypeArray")
    public String baseTypeArray(@RequestParam(value = "id") final String[] id, @RequestParam(value = "name") final String[] name) {
        final StringBuffer result = new StringBuffer();
        Arrays.stream(id).forEach(x -> result.append(String.format("id is %s\n",x)));
        Arrays.stream(name).forEach(x -> result.append(String.format("name is %s\n",x)));
        return result.toString();
    }

    @RequestMapping("/mapFromArray")
    //对于query string放在报文体的情况,请求报文头header需要有 content-type: application/x-www-form-urlencoded,不然的话无法获取query string
    public String mapFromArray(@RequestParam Map<String,String> context) {
        return context.toString();
    }

    @PostMapping("/multiMapFromArray")
    public String multiMapFromArray(@RequestParam MultiValueMap<String,Object> context) {
        return context.toString();
    }

    @PostMapping(value = "/mapFromJSON", consumes = {"application/json"}, produces = {"application/json"})
    public String mapFromJSON(@RequestBody Map<String,String> context) {
        return context.toString();
    }


    @PostMapping(value = "/jsonAsArray", consumes = {"application/json"}, produces = {"application/json"})
    public String jsonAsArray(@RequestBody final List<SimpleJson> persons) {
        final StringBuffer result = new StringBuffer();
        persons.forEach(x -> result.append(x.toString()));
        return result.toString();
    }




}
