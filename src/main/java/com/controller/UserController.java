package com.controller;

import com.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 * 时间：2019年8月16日15:01:02
 */
@Controller
public class UserController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(UserDto userDto){
        return "redirect:/";
    }

    //异步验证
    @RequestMapping("/verification")
    public String verification(){

        return null;
    }

}
