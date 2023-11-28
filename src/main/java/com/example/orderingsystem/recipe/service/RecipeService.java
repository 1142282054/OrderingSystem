package com.example.orderingsystem.recipe.service;

import com.example.orderingsystem.recipe.po.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipe();

    List<Recipe> getReciprById(String ids);
}
