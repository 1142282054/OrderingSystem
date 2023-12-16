package com.example.orderingsystem.comment.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MinZhi
 * @version 1.0
 */
@Controller
public class NotFountController implements ErrorController {

//    public String getErrorPath(){
//        return "/error";
//    }

    /**
     * 异常控制器
     * @return 404页面
     */
    @RequestMapping("/error")
    public String error404(){
        return "error/404";
    }

}
