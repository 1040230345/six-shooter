package com.interceptor;
import com.dto.UserDto;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 对除了主页外的所有页面进行拦截验证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //优先判断是否有session存在
        HttpSession session = request.getSession();
        if (session.getAttribute("User_id") != null) {
            //依靠user_id返回用户资料
            UserDto userDto = userService.findUserByid((int) session.getAttribute("User_id"));
            if (userDto.getAvatar_url() == null) {
                userDto.setAvatar_url("/static/images/superengineer.jpg");
            }
            session.setAttribute("User",userDto);
            //如果session存在，放行
            return true;
        } else {
            //判断cookie是否存在
            Cookie[] cookies = request.getCookies();
            //防止空指针异常
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    //假如用户的状态还是登陆着的
                    if (cookie.getName().equals("TOKEN")) {
                        //依靠cookie查找用户资料，返回前端
                        int user_id = userService.findUserIdByCookie(cookie.getValue());
                        //如果找不到这个用户
                        if (user_id == 0) {
                            //页面跳转
                            response.sendRedirect("/");
                            return false;
                        }
                        //依靠user_id返回用户资料
                        UserDto userDto = userService.findUserByid(user_id);
                        if (userDto.getAvatar_url() == null) {
                            userDto.setAvatar_url("/static/images/superengineer.jpg");
                        }
                        session.setAttribute("User",userDto);
                        //放行
                        return true;
                    }
                }
            }
            //页面跳转
            response.sendRedirect("/");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
