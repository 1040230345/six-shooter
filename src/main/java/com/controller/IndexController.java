package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主页控制器
 * 作者：jackson
 * 时间：2019年8月16日13:47:22
 */
@Controller
public class IndexController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/loginout")
    public String login_out(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("TOKEN")){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        return "index";
    }

}
