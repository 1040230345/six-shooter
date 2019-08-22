package com.controller;

import com.dto.UserDto;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 家的控制器
 * 时间：2019年8月19日22:09:27
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(HttpServletRequest request,Model model){
        //检查home页面是否有cookie
        Cookie[] cookies = request.getCookies();
        //防止空指针异常
        if(cookies!=null){
            for(Cookie cookie:cookies){
                //假如用户的状态还是登陆着的
                if(cookie.getName().equals("TOKEN")){
                    //依靠cookie查找用户资料，返回前端
                    int user_id = userService.findUserIdByCookie(cookie.getValue());
                    //依靠user_id返回用户资料
                    UserDto userDto = userService.findUserByid(user_id);
                    //获取Session
                    HttpSession session=request.getSession();
                    if(userDto.getAvatar_url()==null){
                        userDto.setAvatar_url("/images/superengineer.jpg");
                    }
                    session.setAttribute("user_id",user_id);
                    model.addAttribute("USER",userDto);
                    return "home";
                }
            }
        }
        return "index";
    }
}
