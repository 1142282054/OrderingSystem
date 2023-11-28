package com.example.orderingsystem.user.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.user.po.User;
import com.example.orderingsystem.user.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author MinZhi
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public Result login(User user, @RequestParam("captcha")String captcha, HttpSession session, HttpServletRequest request){

        //验证码验证
        if (!CaptchaUtil.ver(captcha, request)) {
//            CaptchaUtil.clear(request);  // 清除session中的验证码
            return Result.fail();
        }

        User desUser = userService.checkLogin(user);
        if (desUser != null){
            desUser.setPwd(null);
            session.setAttribute("userInfo",desUser);
            return Result.successful();
        }
        return Result.fail();
    }

    @RequestMapping("/init")
    public String initJson(HttpSession session){
        User userInfo = (User) session.getAttribute("userInfo");
//        String initJson =  userService.getInitJson(userInfo.getRole());
        String initJson =  userService.getInitJson(1);
        return initJson;
    }

}
