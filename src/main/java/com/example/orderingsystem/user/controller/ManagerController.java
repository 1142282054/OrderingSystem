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
public class ManagerController {

    @Resource
    RecipeService recipeService;

    @Resource
    OrderService orderService;

    /**
     * 获取打印页面
     * 1.获取用户月度订单
     * 2.存到session域
     * 3.页面跳转
     * @param page 查询信息(分页 查询限制)
     * @param session
     * @return
     */
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

    /**
     * 获取菜谱管理页面
     * @param session
     * @return
     */
    @RequestMapping("/recipeManage")
    public String recipeManage(HttpSession session){
        List<FoodClass> foodClasses = recipeService.getFoodClassList();
        if (foodClasses != null){
            session.setAttribute("foodClassList",foodClasses);
        }
        return "manager/manageRecipe";
    }
}
