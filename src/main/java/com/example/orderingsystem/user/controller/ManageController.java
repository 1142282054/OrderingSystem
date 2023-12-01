package com.example.orderingsystem.user.controller;

import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.service.OrderService;
import com.example.orderingsystem.order.vo.QueryPage;
import com.example.orderingsystem.recipe.po.FoodClass;
import com.example.orderingsystem.recipe.service.RecipeService;
import org.springframework.boot.web.server.Http2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Controller
@RequestMapping("/manager")
public class ManageController {

    @Resource
    RecipeService recipeService;

    @Resource
    OrderService orderService;

    @RequestMapping("/table")
    public String table(QueryPage page, HttpSession session){
        List<Order> orderList = orderService.getOrderList(page);
        session.setAttribute("printList",orderList);
        session.setAttribute("page",page);
        return "manager/table";
    }

    @RequestMapping("/checkOrder")
    public String check(){

        return "manager/checkOrder";
    }

    @RequestMapping("/recipeManage")
    public String recipeManage(HttpSession session){
        List<FoodClass> foodClasses = recipeService.getFoodClassList();
        if (foodClasses != null){
            session.setAttribute("foodClassList",foodClasses);
        }
        return "manager/manageRecipe";
    }
}
