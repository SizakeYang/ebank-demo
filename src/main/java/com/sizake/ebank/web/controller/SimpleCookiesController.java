package com.sizake.ebank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cookies")
public class SimpleCookiesController {

    @RequestMapping("/setBrowser")
    public String setBrowser(@RequestParam("browser") final String browser, final HttpServletRequest request, final HttpServletResponse response, final HttpSession session) {
        //取出session中的browser
        final Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        return "Cookies";
    }

    @RequestMapping("/getBrowser")
    @ResponseBody
    public String getBrowser(@SessionAttribute("browser") final String browser) {
        return browser;
    }
}
