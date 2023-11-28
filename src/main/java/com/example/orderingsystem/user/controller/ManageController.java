package com.example.orderingsystem.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MinZhi
 * @version 1.0
 */
@Controller
@RequestMapping("/manager")
public class ManageController {

    @RequestMapping("/table")
    public String table(){

        return "manager/table";
    }

    @RequestMapping("/checkOrder")
    public String check(){

        return "manager/checkOrder";
    }
}
