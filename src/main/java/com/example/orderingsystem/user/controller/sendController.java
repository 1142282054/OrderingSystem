package com.example.orderingsystem.user.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.service.OrderService;
import com.example.orderingsystem.user.po.Send;
import com.example.orderingsystem.user.po.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/send")
public class sendController {

    @Resource
    OrderService orderService;

    @RequestMapping("/table")
    public String tablePage(){

        return "send/table";
    }

    /**
     * 更新送达状态
     * 1.从session中获取配送员信息
     * 2.添加配送表记录,获取配送id
     * 3.修改订单信息
     * @param order 送达的订单信息
     * @param session
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/commit")
    public Result commit(@RequestBody Order order,HttpSession session) throws ParseException {
        User userInfo = (User) session.getAttribute("userInfo");
        Send send = orderService.addSend(userInfo.getUid());
        boolean commit = orderService.commitOrder(order,send);
        if (commit){
            return Result.successful();
        }
        return Result.fail();
    }
}
