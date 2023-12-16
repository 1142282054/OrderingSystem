package com.example.orderingsystem.comment.config;

import com.example.orderingsystem.comment.intercepter.LoginInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author MinZhi
 * @version 1.0
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier(value = "loginInterception")
    HandlerInterceptor handlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterception());
//      拦截路径
        registration.addPathPatterns("/**");
//      放行路径
        registration.excludePathPatterns(
                "/login",
                "/user/login",
                "/captcha",
                "/layuimini/**",
                "/webjars/**"
        );
    }
}
