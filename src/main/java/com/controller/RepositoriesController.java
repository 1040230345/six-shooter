package com.controller;

import com.dto.UserDto;
import com.mapper.MailMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 仓库控制器
 * 时间：2019年8月23日14:13:49
 */
@Controller
public class RepositoriesController {

    @GetMapping("/newRepo")
    public String newRepo(HttpServletRequest request, Model model){
        //获取session内容返回用户信息
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("User");
        model.addAttribute("USER",userDto);
        return "/newRepo";
    }

}
