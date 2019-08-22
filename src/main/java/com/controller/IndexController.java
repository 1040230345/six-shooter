package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    public String index(HttpServletRequest request){
        //如果页面存在cookie，而且用户一定要登陆的话,只能让他滚了
        Cookie[] cookies = request.getCookies();
        //防止空指针异常
        if(cookies!=null){
            for(Cookie cookie:cookies){
                //假如用户的状态还是登陆着的
                if(cookie.getName().equals("TOKEN")){
                    //请回主页谢谢
                    return "redirect:/home";
                }
            }
        }
        return "index";
    }



}
