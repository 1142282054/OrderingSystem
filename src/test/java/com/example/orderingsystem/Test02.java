package com.example.orderingsystem;

import com.example.orderingsystem.comment.vo.Page;
import com.example.orderingsystem.order.mapper.OrderMapper;
import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.service.impl.OrderServiceImpl;
import com.example.orderingsystem.order.vo.QueryPage;
import com.example.orderingsystem.recipe.mapper.RecipeMapper;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.vo.RecipePage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@SpringBootTest
public class Test02 {

    @Resource
    RecipeMapper recipeMapper;

    @Resource
    OrderMapper orderMapper;

    @Test
    void test01(){
        List<Recipe> recipeByids = recipeMapper.getRecipeByIds("1.2,3");
        recipeByids.forEach(System.out::println);
    }

    @Test
    void test02(){
        QueryPage queryPage = new QueryPage();
        queryPage.setUid(1);
        queryPage.setPage(1);
        queryPage.setLimit(10);

        List<Order> orderList = orderMapper.getOrders(queryPage);
        System.out.println(orderList);
    }

    @Test
    void test03(){
        RecipePage recipePage = new RecipePage();
        recipePage.setPage(1);
        recipePage.setLimit(15);
        recipePage.setName("米");
        List<Recipe> recipeByids = recipeMapper.getRecipeList(recipePage);
        recipeByids.forEach(System.out::println);
    }

    @Test
    void test04(){
        Path path = Paths.get("/pic");
        System.out.println(path);
    }
}
