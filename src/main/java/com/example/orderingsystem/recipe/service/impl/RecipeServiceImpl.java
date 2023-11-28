package com.example.orderingsystem.recipe.service.impl;

import com.example.orderingsystem.recipe.mapper.RecipeMapper;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.service.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Service
public class RecipeServiceImpl implements RecipeService {

    @Resource
    RecipeMapper recipeMapper;

    @Override
    public List<Recipe> getAllRecipe() {
        List<Recipe> recipeList = recipeMapper.getAllRecipe();
        return recipeList;
    }

    @Override
    public List<Recipe> getReciprById(String ids) {
        List<Recipe> recipeList = recipeMapper.getRecipeByIds(ids);
        return recipeList;
    }
}
