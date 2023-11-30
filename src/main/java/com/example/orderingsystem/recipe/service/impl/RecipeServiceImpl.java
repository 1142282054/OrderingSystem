package com.example.orderingsystem.recipe.service.impl;

import com.example.orderingsystem.recipe.mapper.FoodClassMapper;
import com.example.orderingsystem.recipe.mapper.RecipeMapper;
import com.example.orderingsystem.recipe.po.FoodClass;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.service.RecipeService;
import com.example.orderingsystem.recipe.vo.RecipePage;
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

    @Resource
    FoodClassMapper foodClassMapper;

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

    @Override
    public List<Recipe> getRecipeList(RecipePage page) {
        List<Recipe> recipeList = recipeMapper.getRecipeList(page);
        return recipeList;
    }

    @Override
    public Recipe getRecipeByCid(Integer cid) {
        Recipe recipe = recipeMapper.getRecipeByCid(cid);
        return null;
    }

    @Override
    public List<FoodClass> getFoodClassList() {
        List<FoodClass> foodClassList = foodClassMapper.getFoodClassList();
        return foodClassList;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        Integer add = recipeMapper.addRecipe(recipe);
        if (add > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRecipes(String ids) {
        Integer delete = recipeMapper.deleteRecipes(ids);
        if (delete > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRecipe(Recipe recipe) {
        Integer update = recipeMapper.updateRecip(recipe);
        if (update > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addRecipePic(byte[] pic,String name) {
        Integer update = recipeMapper.addRecipePic(pic,name);
        if (update > 0){
            return true;
        }
        return false;
    }

    @Override
    public byte[] getRecipePic(Integer cid) {
        byte[] data = recipeMapper.getRecipePic(cid);
        return data;
    }

    @Override
    public Recipe getRecipeById(Integer cid) {
        Recipe data = recipeMapper.getRecipeById(cid);
        return data;
    }
}
