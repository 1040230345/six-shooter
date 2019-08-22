package com.controller;

import com.dto.UserDto;
import com.mapper.MailMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户控制器
 * 时间：2019年8月16日15:01:02
 * 修改时间:2019年8月22日16:34:16
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailMapper mailMapper;


    /**
     * 登陆逻辑
     * @param email_or_name
     * @param password
     * @param model
     * @param response
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam(required = false) String remember, String email_or_name ,String password,String Vcode,Model model,HttpServletResponse response,HttpServletRequest request) {
        //优先验证验证码是是否正确
        boolean bl = userService.checkCode(email_or_name,Vcode);
        if(bl){ }else {
            model.addAttribute("login_error","验证码错误");
            return "index";
        }
        //验证登录账号密码
        UserDto userDto = userService.checkLogin(email_or_name,password);
        if(userDto != null){
            //如果用户选择记住自己
            if(remember!=null){
                //创建cookie并且保存在数据库
                String token = userService.updateCookie(userDto);
                if(token!=null){
                    model.addAttribute("USER",userDto);
                    //创建新cookie
                    Cookie cookie = new Cookie("TOKEN",token);
                    //发送给浏览器
                    response.addCookie(cookie);
                    //删除验证码记录
                    userService.delCode(email_or_name);
                }
            }
            //获取Session
            HttpSession session=request.getSession();
            //添加到session里面
            session.setAttribute("User_id",userDto.getId());
            return "redirect:/home";
        }
        model.addAttribute("login_error","账号或密码错误");
        return "index";
    }

    /**
     * 注册逻辑
     * @param userDto
     * @param model
     * @return
     */
    @PostMapping("/register")
    public String register(UserDto userDto, Model model,String Vcode,HttpServletRequest request) {
        //优先判断验证码是否正确
        boolean bl = userService.checkCode(userDto.getEmail(),Vcode);
        if(bl){
            //获取用户信息
            userDto = userService.register(userDto);
            if(userDto!=null){
                //返回用户信息
                model.addAttribute("USER",userDto);
                //删除验证码
                mailMapper.delCodeByEmail(userDto.getEmail());
                //获取Session
                HttpSession session=request.getSession();
                //添加到session里面
                session.setAttribute("User_id",userDto.getId());
                return "redirect:/home";
            }else {
                model.addAttribute("regisrer_error","服务器繁忙,稍后再注册");
                return  "index";
            }
        }else {
            model.addAttribute("regisrer_error","验证码错误");
            return  "index";
        }
    }

    /**
     * 异步验证
     * @param email
     * @param name
     * @return.
     */
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
    /**
     * 退出登陆的逻辑页面
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/loginout")
    public String login_out(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("TOKEN")){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        return "redirect:/";
    }

    /**
     * 个人页面访问
     * @return
     */
    @GetMapping("/user")
    public String Personal_information(HttpServletRequest request,Model model){
//        Cookie[] cookies = request.getCookies();
//        //防止空指针异常
//        if(cookies!=null){
//            for(Cookie cookie:cookies){
//                //假如用户的状态还是登陆着的
//                if(cookie.getName().equals("TOKEN")){
//                    //获取页面用户id
//                    HttpSession session=request.getSession();
//                    int user_id = (int)session.getAttribute("user_id");
//                    //判断是否和数据库的一致
//                    boolean bl = userService.checkCookieAndChange(user_id, cookie.getValue());
//                    //请回主页谢谢
//                    return "redirect:/user";
//                }
//            }
//        }
//        return "index";
        return "/user";
    }
}

