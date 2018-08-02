package com.sizake.ebank.web.jsonObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor//-->当有@AllArgsConstructor时,要有@NoArgsConstructor,否则会出现Jackson对RequestBody反序列化失败
@AllArgsConstructor


public class ValidObject {

//    @Null	只能是null
//    @NotNull 不能为null 注意用在基本类型上无效，基本类型有默认初始值
//    @AssertFalse  必须为false
//    @AssertTrue	必须是true
//
//    字符串/数组/集合检查：(字符串本身就是个数组)
//    @Pattern(regexp="reg") 验证字符串满足正则
//    @Size(max, min) 验证字符串、数组、集合长度范围
//    @NotEmpty 验证字符串不为空或者null
//    @NotBlank
//    验证字符串不为null或者trim()后不为空
//
//    数值检查：同时能验证一个字符串是否是满足限制的数字的字符串
//    @Max 规定值得上限int
//    @Min
//    规定值得下限
//    @DecimalMax("10.8")	以传入字符串构建一个BigDecimal，规定值要小于这个值
//    @DecimalMin 可以用来限制浮点数大小
//    @Digits(int1, int2) 限制一个小数，整数精度小于int1；小数部分精度小于int2
//    @Digits 无参数，验证字符串是否合法
//    @Range(min=long1,max=long2) 检查数字是否在范围之间
//    这些都包括边界值
//
//    日期检查：Date/Calendar
//    @Past 限定一个日期，日期必须是过去的日期
//    @Future 限定一个日期，日期必须是未来的日期
//
//    其他验证：
//    @Vaild 递归验证，用于对象、数组和集合，会对对象的元素、数组的元素进行一一校验
//    @Email
//    用于验证一个字符串是否是一个合法的右键地址，空字符串或null算验证通过
//    @URL(protocol=,host=,port=,regexp=,flags=) 用于校验一个字符串是否是合法URL

    //hibernate-validator-5.3.6.Final-sources.jar!\org\hibernate\validator\ValidationMessages_zh_CN.properties -->默认配置的错误信息

    //{
    //    "notnull": "     ",
    //    "mustFalse": false,
    //    "onlyEn": "xxxx",
    //    "isNonProfit": true,
    //    "notBlank": "asdasd",
    //    "intIn10to99": 44,
    //    "numberIn77to107": 88.333,
    //    "past": "2017-09-09",
    //    "future": "2027-09-09",
    //    "email": "sizae@sina.cn",
    //    "url": "http://127.0.0.1",
    //    "persons": [
    //        {
    //            "name": "Google",
    //            "age": 20
    //        },
    //        {
    //            "name": "Baidu",
    //            "age": 20
    //        },
    //        {
    //            "name": "SoSo",
    //            "age": 20
    //        }
    //    ]
    //}

    @NotNull
    private String notnull;

    @AssertFalse
    private boolean mustFalse;

    @Pattern(regexp="^[A-Za-z]+$")
    private String onlyEn;


    @NotBlank
    private String notBlank;

    @Range(min = 10, max = 99)
    private Integer intIn10to99;

    @DecimalMax("107.7")
    @DecimalMin("77.4")
    @Digits(integer = 3, fraction = 3)
    private BigDecimal numberIn77to107;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date past;

    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date future;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @URL
    private String url;

    @Valid
    @Size(min = 2,max = 3)
    private List<SimpleJson> persons;


    //自定义[校验注解]需要:
    // 1:自定义一个 @interface,必须要有的属性为 message、groups、payload
    // message:校验不通过时显示的信息
    // group:校验是否执行的分组
    // payload:附带信息
    // 2:实现 ConstraintValidator 接口






}
