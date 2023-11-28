package com.example.orderingsystem.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MinZhi
 * @version 1.0
 */
@Controller
public class PageController {

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/index")
    public String indexPage(){
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcomePage(){
        return "welcome";
    }


}
