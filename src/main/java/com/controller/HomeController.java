package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 家的控制器
 * 时间：2019年8月19日22:09:27
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        //检查home页面是否有cookie
        Cookie[] cookies = request.getCookies();
        //防止空指针异常
        if(cookies!=null){
            for(Cookie cookie:cookies){
                //假如用户的状态还是登陆着的
                if(cookie.getName().equals("TOKEN")){

                }
            }
        }
        return "index";
    }
}
