package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 家的控制器
 * 时间：2019年8月19日22:09:27
 */
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "/home";
    }
}