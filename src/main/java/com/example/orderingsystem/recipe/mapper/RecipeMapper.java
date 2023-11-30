package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.vo.RecipePage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecipeMapper {
    List<Recipe> getAllRecipe();

    List<Recipe> getRecipeByIds(@Param("ids") String ids);

    List<Recipe> getRecipeList(RecipePage page);

    Recipe getRecipeByCid(Integer cid);

    Integer addRecipe(Recipe recipe);

    Integer deleteRecipes(String ids);

    Integer updateRecip(Recipe recipe);

    Integer addRecipePic(byte[] pic,String name);

    byte[] getRecipePic(Integer cid);

    Recipe getRecipeById(Integer cid);
}
