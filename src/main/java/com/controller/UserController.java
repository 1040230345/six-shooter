package com.controller;

import com.dto.UserDto;
import com.mapper.MailMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource(name = "userRedisTemplate")
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 登陆逻辑
     * @param email_or_name
     * @param password
     * @param model
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(@RequestParam(required = false) String remember, String email_or_name ,String password,String Vcode,Model model,HttpServletResponse response,HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        //用户名或者邮箱进行解密
        //email_or_name = userService.decrypt(email_or_name);
        //System.out.println(email_or_name);
        //优先验证验证码是是否正确
        boolean bl = userService.checkCode(email_or_name,Vcode);
        if(bl){ }else {
//            model.addAttribute("login_error","验证码错误");
            map.put("login_error","验证码错误");
            return map;
        }
        //验证登录账号密码
        UserDto userDto = userService.checkLogin(email_or_name,password);
        if(userDto != null){
            //创建token，缓存用户数据
            String token = userService.updateCookie(userDto);
            //model.addAttribute("USER",userDto);
            //创建新cookie
            Cookie cookie = new Cookie("TOKEN",token);
            //发送给浏览器
            response.addCookie(cookie);
//            //获取Session
//            HttpSession session=request.getSession();
//            //添加到session里面
//            session.setAttribute("User_id",userDto.getId());
            map.put("loginSuccess","success");
            return map;
        }
//        model.addAttribute("login_error","请检查密码后再次尝试登陆，谢谢");
        map.put("login_error","请检查密码后再次尝试登陆，谢谢");
        return map;
    }

    /**
     * 注册逻辑
     * @param userDto
     * @param model
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Map<String, String> register(UserDto userDto, Model model,String Vcode,HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        //解密
        userDto.setEmail(userService.decrypt(userDto.getEmail()));
        //System.out.println(userDto.toString());
        //优先判断验证码是否正确
        boolean bl = userService.checkCode(userDto.getEmail(),Vcode);
        if(bl){
            //userDto.setName(userService.decrypt(userDto.getName()));
            //持久化用户信息
            userDto = userService.register(userDto);
            if(userDto!=null){
                //缓存用户信息
                stringRedisTemplate.opsForValue().set(userDto.getName(),userDto.getEmail());
                stringRedisTemplate.opsForSet().add("ALL_EMAIL",userDto.getEmail());
                //创建令牌
                String token = userService.updateCookie(userDto);
                //创建新cookie
                Cookie cookie = new Cookie("TOKEN",token);
                //发送给浏览器
                response.addCookie(cookie);
                //删除验证码
                //mailMapper.delCodeByEmail(userDto.getEmail());
                //获取Session
                //HttpSession session=request.getSession();
                //添加到session里面
                //session.setAttribute("User_id",userDto.getId());
                map.put("registerSuccess","success");
                return map;
            }else {
//                model.addAttribute("regisrer_error","服务器繁忙,稍后再注册");
                map.put("register_error","服务器繁忙,稍后再注册");
                return  map;
            }
        }else {
//            model.addAttribute("regisrer_error","验证码错误");
            map.put("register_error","验证码错误");
            return  map;
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
            //解密
            email = userService.decrypt(email);
//            System.out.println(email);
            //缓存中判断
            boolean bl = stringRedisTemplate.opsForSet().isMember("ALL_EMAIL",email);
            //UserDto userDto = userService.findByEmail(email);

            if (bl) {
                map.put("email", "0");
                return map;
            }
            map.put("email", "1");
            return map;
        }

        if (name != null) {

            //解密
            //name = userService.decrypt(name);
            //System.out.println(name);
            //缓存中查找
            boolean bl = stringRedisTemplate.hasKey(name);
            //UserDto userDto = userService.findByName(name);

            if (bl) {
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
                    //删除缓存
                    redisTemplate.delete(cookie.getValue());
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        //销毁session
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

    /**
     * 个人页面访问
     * @return
     */
    @GetMapping("/{user_name}")
    public String Personal_information(@PathVariable("user_name") String  user_name,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("User");
        //判断下是不是自己咯
        if(userDto.getName().equals(user_name)){
            model.addAttribute("USER",userDto);
            return "/personal";
        }
        model.addAttribute("USER",userDto);
        return "redirect:/home";

    }

    /**
     * 个人页面选项卡索引
     */
    @GetMapping("/nav")
    public String getUrl(String index){
        return index;
    }
}

