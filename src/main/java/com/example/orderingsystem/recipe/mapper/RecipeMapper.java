package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecipeMapper {
    List<Recipe> getAllRecipe();

    List<Recipe> getRecipeByIds(@Param("ids") String ids);
}
