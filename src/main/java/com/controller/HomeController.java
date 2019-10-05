package com.controller;

import com.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 家的控制器
 * 时间：2019年8月19日22:09:27
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("User");
        model.addAttribute("USER",userDto);
        return "/home";
    }
}
