package com.example.orderingsystem.user.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.user.po.User;
import com.example.orderingsystem.user.service.UserService;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author MinZhi
 * @version 1.0
 */
//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 登录功能
     * 1.检查验证码是否正确
     * 2.验证用户信息(service层)
     *  -成功:把用户信息存入session域,并返回成功信息
     *  -失败:返回失败信息
     * @param user 用户信息
     * @param captcha 验证码
     * @param session
     * @param request
     * @return 登录验证的确认信息
     */
    @ResponseBody
    @RequestMapping("/login")
    public Result login(User user, @RequestParam("captcha")String captcha, HttpSession session, HttpServletRequest request){

        //验证码验证
//        if (!CaptchaUtil.ver(captcha, request)) {
////            CaptchaUtil.clear(request);  // 清除session中的验证码
//            return Result.fail();
//        }

        User desUser = userService.checkLogin(user);
        if (desUser != null){
            desUser.setPwd(null);
            session.setAttribute("userInfo",desUser);
            return Result.successful();
        }
        return Result.fail();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    /**
     * 获取主页初始化信息
     * 1.从session中获取权限等级
     * 2.根据权限等级获取不同的初始化信息(service)
     * 3.返回权限信息
     * @param session
     * @return 权限信息
     */
    @ResponseBody
    @RequestMapping("/init")
    public String initJson(HttpSession session){
        User userInfo = (User) session.getAttribute("userInfo");
//        (1)从Redis中获取
//        String initJson =  userService.getInitJson(userInfo.getRole());
//        (2)从mysql中获取
        String initJson =  userService.getInitJsonByMysql(userInfo.getRole());


        return initJson;
    }

}
