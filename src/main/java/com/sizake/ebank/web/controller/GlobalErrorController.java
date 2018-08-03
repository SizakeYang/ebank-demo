package com.sizake.ebank.web.controller;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

//统一异常处理
// 其实还有另外一种方式,就是@ExceptionHandler + @ControllerAdvice,获取异常是通过方法参数注入,缺点是无法处理404错误/异常{因为SpringMVC优先处理（Try Catch）掉了资源映射不存在的404类错误/异常，虽然在响应信息注入了404的HttpStatus通信信息，但木有了异常，肯定不会进入@ExceptionHandler 的处理逻辑}
@Controller //继承于BasicErrorController，注意一定要添加@Controller,不然Spring无法感知自定义的bean,BasicErrorController还是会起作用
public class GlobalErrorController extends BasicErrorController {

    public GlobalErrorController(final ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError()); // Xnew DefaultErrorAttributes() 必须自己指定
    }

//    {
//        "timestamp": 1533267262969,
//            "status": 400,
//            "error": "Bad Request",
//            "exception": "org.springframework.web.bind.MethodArgumentNotValidException",
//            "errors": [
//        {
//            "codes": [
//            "Email.validObject.email",
//                    "Email.email",
//                    "Email.java.lang.String",
//                    "Email"
//            ],
//            "arguments": [
//            {
//                "codes": [
//                "validObject.email",
//                        "email"
//                    ],
//                "arguments": null,
//                    "defaultMessage": "email",
//                    "code": "email"
//            },
//                [ ],
//            {
//                "arguments": null,
//                    "codes": [
//                ".*"
//                    ],
//                "defaultMessage": ".*"
//            }
//            ],
//            "defaultMessage": "不是一个合法的电子邮件地址",
//                "objectName": "validObject",
//                "field": "email",
//                "rejectedValue": "sizaesina.cn",
//                "bindingFailure": false,
//                "code": "Email"
//        }
//    ],
//        "message": "Validation failed for object='validObject'. Error count: 1",
//            "path": "/ebank/valid/checkInput1"
//    }

    /**
     * 覆盖默认的Json响应
     */
    // 对于BasicErrorController,除了请求头Accept包含text/html返回对应html页面外,其他均返回json格式
    // 有需要可以根据请求是否为ajax请求,方法为 XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    @Override
    public ResponseEntity<Map<String, Object>> error(final HttpServletRequest request) {
        final Map<String, Object> body = this.getErrorAttributes(request,
                this.isIncludeStackTrace(request, MediaType.ALL));
        //获取异常:body里的exception属性,String:body.get("exception")

        //自定义返回的json格式
        body.put("ebank-error", true);
        body.put("localdate", LocalDateTime.now().toString());

        final HttpStatus status = this.getStatus(request);

        return new ResponseEntity<>(body, status);
    }


}
