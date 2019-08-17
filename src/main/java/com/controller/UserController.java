package com.controller;

import com.dto.CookieDto;
import com.dto.UserDto;
import com.my_util.GetTime_util;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public String login(String email_or_name ,String password) {

        //如果页面存在cookie，而且用户一定要登陆的话

        UserDto userDto = userService.findUser_login(email_or_name,password);

        if(userDto!=null){

        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }


    /**
     * 注册逻辑
     * @param userDto
     * @param model
     * @param httpServletResponse
     * @return
     */
    @PostMapping("/register")
    public String register(UserDto userDto, Model model, HttpServletResponse httpServletResponse) {

        // 赋值创建时间和修改时间
        userDto.setCreated_at(getTime_util.GetNowTime_util());
        userDto.setUpdated_at(getTime_util.GetNowTime_util());

        //插入数据库
        int num = userService.insertUser(userDto);

        //插入成功
        if(num>0){
            //获取用户信息
            userDto = userService.findByName(userDto.getName());
            model.addAttribute("USER",userDto);
            //获取UUID
            String token = UUID.randomUUID().toString();
            //插入cookies数据库
            CookieDto cookieDto = new CookieDto();
            cookieDto.setCookie(token);
            cookieDto.setUser_id(userDto.getId());
            cookieDto.setCreated_at(getTime_util.GetNowTime_util());
            cookieDto.setUpdated_at(getTime_util.GetNowTime_util());
            int inser = userService.inserCookie(cookieDto);
            if(inser>0){
                //创建新cookie
                Cookie cookie = new Cookie("TOKEN",token);
                //发送给浏览器
                httpServletResponse.addCookie(cookie);
                return "redirect:/";
            }

            return  "register";

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
            UserDto userDto = userService.findByEmail(email);

            if (userDto!=null) {
                map.put("email", "0");
                return map;
            }
            map.put("email", "1");
            return map;
        }

        if (name != null) {
            UserDto userDto = userService.findByName(name);

            if (userDto!=null) {
                map.put("name", "0");
                return map;
            }
            map.put("name", "1");
            return map;

        }
        return map;
    }
}

