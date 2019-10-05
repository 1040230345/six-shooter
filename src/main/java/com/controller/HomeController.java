package com.controller;

import com.dto.UserDto;
import com.my_util.GetCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 家的控制器
 * 时间：2019年8月19日22:09:27
 */
@Controller
public class HomeController {
    @Resource(name = "userRedisTemplate")
    private RedisTemplate redisTemplate;
    @Autowired
    private GetCookies cookies;

    @GetMapping("/home")
    public String home(HttpServletRequest request,Model model) {
//        HttpSession session = request.getSession();
//        UserDto userDto = (UserDto) session.getAttribute("User");
//        model.addAttribute("USER",userDto);
        /**
         * 逻辑修改，从缓存中取值
         */
        Cookie cookie = cookies.getCookie("TOKEN",request);
        UserDto userDto =(UserDto)redisTemplate.opsForValue().get(cookie.getValue());
        model.addAttribute("USER",userDto);
        return "/home";
    }
}
