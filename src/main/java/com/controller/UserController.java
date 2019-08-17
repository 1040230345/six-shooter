package com.controller;

import com.dto.UserDto;
import com.mapper.UserMapper;
import com.my_util.GetTime_util;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 * 时间：2019年8月16日15:01:02
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GetTime_util getTime_util;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDto userDto) {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserDto userDto, Model model) {

        // 赋值创建时间和修改时间
        userDto.setCreated_at(getTime_util.GetNowTime_util());
        userDto.setUpdated_at(getTime_util.GetNowTime_util());

        //插入数据库
        int num = userService.insertUser(userDto);

        if(num>0){
            return "redirect:/";
        }

        model.addAttribute("regisrer_error","服务器繁忙,稍后再注册");

        return  "register";


    }

    //异步验证
    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> verification(@RequestParam(required = false) String email,
                                            @RequestParam(required = false) String name) {

        Map<String, String> map = new HashMap<>();

        if (email != null) {
            int num = userService.findByEmail(email);

            if (num>0) {
                map.put("email", "0");
                return map;
            }
            map.put("email", "1");
            return map;
        }

        if (name != null) {
            int num = userService.findByName(name);

            if (num>0) {
                map.put("name", "0");
                return map;
            }
            map.put("name", "1");
            return map;

        }
        return map;
    }
}

