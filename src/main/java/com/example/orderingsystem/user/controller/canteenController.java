package com.example.orderingsystem.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MinZhi
 * @version 1.0
 */
@Controller
@RequestMapping("/canteen")
public class canteenController {

    @RequestMapping("/table")
    public String orderPage(){
        return "canteen/table";
    }
}
