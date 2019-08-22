package com.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 */
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/","/error","/static/**","/login","/register","/verification","/vcodeSend");

//        InterceptorRegistration interceptorRegistration = registry.addInterceptor(sessionInterceptor);
//        //不拦截页面
//        interceptorRegistration.excludePathPatterns("/error");
//        interceptorRegistration.excludePathPatterns("/static/**");
//        interceptorRegistration.excludePathPatterns("/");
//        //拦截页面
//        interceptorRegistration.addPathPatterns("/**");
    }
}
