package com.example.orderingsystem.recipe.service;

import com.example.orderingsystem.recipe.po.FoodClass;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.vo.RecipePage;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipe();

    List<Recipe> getReciprById(String ids);

    List<Recipe> getRecipeList(RecipePage page);

    Recipe getRecipeByCid(Integer cid);

    List<FoodClass> getFoodClassList();

    boolean addRecipe(Recipe recipe);

    boolean deleteRecipes(String ids);

    boolean updateRecipe(Recipe recipe);

    boolean addRecipePic(byte[] pic,String name);

    byte[] getRecipePic(Integer cid);

    Recipe getRecipeById(Integer cid);
}
