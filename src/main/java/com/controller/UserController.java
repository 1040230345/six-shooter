package com.controller;

import com.dto.UserDto;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 * 时间：2019年8月16日15:01:02
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

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
    @RequestMapping(value = "/verification",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> verification(@RequestParam(required=false) String email,
                                            @RequestParam(required=false) String name){

        Map<String,String> map = new HashMap<>();

        if(email!=null){
            if(email.equals("666")){
                map.put("email", "1");
                return map;
            }
            map.put("email", "0");
            return map;
        }

        if(name!=null){
            if(name.equals("666")){
                map.put("name", "1");
                return map;
            }
            map.put("name", "0");
            return map;

        }




    }



}
