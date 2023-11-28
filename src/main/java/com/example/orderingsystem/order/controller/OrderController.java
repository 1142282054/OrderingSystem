package com.example.orderingsystem.order.controller;


import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.service.OrderService;
import com.example.orderingsystem.order.vo.QueryPage;
import com.example.orderingsystem.recipe.po.TmpOrder;
import com.example.orderingsystem.recipe.service.MenuService;
import com.example.orderingsystem.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Resource
    MenuService menuService;

    @Resource
    OrderService orderService;

    @ResponseBody
    @RequestMapping("/tmpOrder")
    public Result tmpOrder(TmpOrder tmpOrder, HttpSession session) throws ParseException {

        User userInfo = (User) session.getAttribute("userInfo");

        boolean change = menuService.changeTmpOrder(tmpOrder,userInfo.getUid());
        if (change){
            return Result.successful();
        }
        return Result.fail();
    }

    @ResponseBody
    @RequestMapping("/addOrder")
    public Result addOrder(@RequestBody List<TmpOrder> tmpOrderList, HttpSession session) throws ParseException {
//    public Result addOrder(@RequestBody List<Long> number) throws ParseException {
        log.info(tmpOrderList.toString());
            User userInfo = (User) session.getAttribute("userInfo");
        if (tmpOrderList != null){
            orderService.addOrderList(tmpOrderList,userInfo.getUid());
            menuService.deleteTmpOrder(userInfo.getUid());
            return Result.successful();
        }
        return Result.fail();
    }

    @ResponseBody
    @RequestMapping("/getOrders")
    public Result getOrders(QueryPage page, HttpSession session){
        User userInfo = (User) session.getAttribute("userInfo");
        if (userInfo.getRole() == 2){
            page.setUid(userInfo.getUid());
        }
        List<Order> orderList = orderService.getOrderList(page);
        if (orderList != null){
            return Result.successful(orderList, (long) orderList.size());
        }
        return Result.fail();
    }

}
