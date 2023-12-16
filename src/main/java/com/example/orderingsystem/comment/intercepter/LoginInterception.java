package com.example.orderingsystem.comment.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Component
public class LoginInterception implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userInfo = request.getSession().getAttribute("userInfo");
        if(userInfo == null){
            log.debug("========不放行========" + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        log.info("========放行=========" + request.getRequestURI());
        return true;
    }
}
