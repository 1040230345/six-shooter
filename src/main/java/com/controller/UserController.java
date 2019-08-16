package com.controller;

import com.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户登陆控制器
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

}
