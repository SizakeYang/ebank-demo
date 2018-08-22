package com.sizake.ebank.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sizake.ebank.web.jsonObject.ComplexObject;
import com.sizake.ebank.web.jsonObject.SimpleJson;
import com.sizake.ebank.web.jsonObject.ValidObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/valid")
public class ValidController {


    @RequestMapping(value = "/getObjectMessage", produces = {"application/json"})
    public String getObjectMessage() throws JsonProcessingException {
        final ComplexObject obj = new ComplexObject();

        obj.setB1(new BigDecimal("18.8"));
        obj.setD1(new Date());
        obj.setI1(3);
        obj.setS1("str1");

        final String[] a1 = new String[]{"x1", "x2", "x3"};

        final List<String> l1 = new ArrayList();
        l1.add("y1");
        l1.add("y2");
        l1.add("y3");

        obj.setA1(a1);
        obj.setLs1(l1);

        final Map<String, String> m1 = new HashMap<>();
        m1.put("1", "m1");
        m1.put("2", "m2");
        m1.put("3", "m2");

        obj.setMss1(m1);

        final SimpleJson c1 = new SimpleJson("c1", 1);
        final SimpleJson c2 = new SimpleJson("c2", 2);
        final SimpleJson c3 = new SimpleJson("c3", 3);

        final SimpleJson[] a01 = new SimpleJson[]{c1, c2, c3};
        obj.setA01(a01);

        final List<SimpleJson> lo1 = new ArrayList<>();
        lo1.add(c1);
        lo1.add(c2);
        lo1.add(c3);

        obj.setLo1(lo1);

        final Map<String, List<SimpleJson>> mslo1 = new HashMap<>();
        mslo1.put("lo1", lo1);
        mslo1.put("lo2", lo1);
        mslo1.put("lo3", lo1);

        obj.setMslo1(mslo1);

        final List<Map<String, String>> lmss1 = new ArrayList<>();
        lmss1.add(m1);
        lmss1.add(m1);
        lmss1.add(m1);

        obj.setLmss1(lmss1);

        final List<Map<String, List<SimpleJson>>> lmslo1 = new ArrayList<>();
        lmslo1.add(mslo1);
        lmslo1.add(mslo1);
        lmslo1.add(mslo1);

        obj.setLmslo1(lmslo1);


        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);//格式化输出json-->writerWithDefaultPrettyPrinter()
    }

    @PostMapping(value = "/checkInput1", consumes = {"application/json"})
    public String checkInput1(@RequestBody @Valid final ValidObject obj) {
        return "success";
    }

    //sErrors/BindingResult argument is expected to be declared immediately after the model attribute, the @RequestBody or the @RequestPart arguments
    //注意：每一个@Valid后面必须跟一个BindingResult，验证失败的放到紧跟他的BindingResult中
    @RequestMapping("/checkInput2")
    public ResponseEntity<?> checkInput2(@RequestBody @Valid final ValidObject obj, final BindingResult n) {
        final List<String> errorMsg = new ArrayList<>();
        if (n.hasErrors()) {
            final List<FieldError> fieldErrors1 = n.getFieldErrors();
            for (final FieldError fieldError : fieldErrors1) {
                errorMsg.add(fieldError.getField() + " " + fieldError.getObjectName() + " " + fieldError.getDefaultMessage());
            }
        }
        return ResponseEntity.ok(errorMsg);
    }

    @PostMapping(value = "/checkInput3", consumes = {"application/json"})
    public String checkInput3(@RequestBody @Validated({SimpleJson.Adult.class}) final SimpleJson obj) {
        return "success";
    }


}
