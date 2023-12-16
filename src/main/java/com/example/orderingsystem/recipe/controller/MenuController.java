package com.example.orderingsystem.recipe.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.recipe.po.Menu;
import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.service.MenuService;
import com.example.orderingsystem.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/menu")
public class MenuController {

    @Resource
    RecipeService recipeService;

    @Resource
    MenuService menuService;

    /**
     * 批量添加
     * 1.根据ids获取菜谱列表信息
     * 2.获取菜单id(service)
     * 3.将菜谱信息插入到菜单子表中(service)
     * @param ids 菜id列表字符串 例:1,2,3
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/addMenu")
    public Result addMenu(String ids) throws ParseException {
//        log.info(ids);
        if (ids == ""){
            return Result.fail();
        }
//        根据id获得caipu
        List<Recipe> recipeList = recipeService.getReciprById(ids);
//        插入菜单，获取菜单主键(顺便检查下有没有今天有没有插入过)
        Integer mid = menuService.addMenu();
        if (mid == null){
            return Result.fail();
        }
//        将菜谱批量插入菜单
        boolean insert = menuService.addMenuItems(recipeList,mid);
        if (insert){
            return Result.successful("插入成功");
        }
        return Result.fail();
    }

}
