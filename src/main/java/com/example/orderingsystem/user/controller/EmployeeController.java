package com.example.orderingsystem.user.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.TmpOrder;
import com.example.orderingsystem.recipe.service.MenuService;
import com.example.orderingsystem.user.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    MenuService menuService;

    /**
     * 获取点餐页面
     * 1.获取今日的菜单的菜列表
     * 2.存入session域中
     * 3.跳转页面
     * @param session
     * @return
     * @throws ParseException 时间转换异常
     */
    @GetMapping("/order")
    public String orderPage(HttpSession session) throws ParseException {
        List<MenuItem> menuList = menuService.getMenuByDate();
        if (menuList != null){
            session.setAttribute("menuList",menuList);
        }
        return "employee/order";
    }

    /**
     * 获取临时点餐表信息
     * 1.从session与中获取用户id
     * 2.根据用户id获取选中的菜列表
     * @param session
     * @return 临时点餐表信息
     */
    @ResponseBody
    @RequestMapping("/tmpOrder")
    public Result tmpOrder(HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        log.info(user.getUid().toString());
        List<TmpOrder> tmpOrder =menuService.getTmpOrder(user.getUid());
        if (tmpOrder != null){
            return Result.successful(tmpOrder,0L);
        }
        return Result.fail("数据库没有数据");
    }

    @RequestMapping("/table")
    public String tablePage(){
        return "employee/table";
    }
}
