package com.example.orderingsystem.recipe.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Resource
    RecipeService recipeService;

    @RequestMapping("/page")
    public String recipe(HttpSession session){
        List<Recipe> recipeList = recipeService.getAllRecipe();
//        log.info(recipeList.toString());
        session.setAttribute("recipeList",recipeList);
        return "manager/recipe";
    }

}
