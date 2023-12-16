package com.example.orderingsystem.recipe.service;

import com.example.orderingsystem.recipe.po.FoodClass;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.vo.RecipePage;

import java.util.List;

public interface RecipeService {
    /**
     * 获取所有菜谱
     * @return
     */
    List<Recipe> getAllRecipe();

    /**
     * 通过id获取菜谱
     * @param ids
     * @return
     */
    List<Recipe> getReciprById(String ids);

    /**
     * 根据限制条件获取菜谱
     * @param page
     * @return
     */
    List<Recipe> getRecipeList(RecipePage page);

    /**
     * 根据id获取菜谱
     * @param cid
     * @return
     */
    Recipe getRecipeByCid(Integer cid);

    /**
     * 获取所有菜系列表
     * @return
     */
    List<FoodClass> getFoodClassList();

    /**
     * 添加菜谱
     * @param recipe
     * @return
     */
    boolean addRecipe(Recipe recipe);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteRecipes(String ids);

    /**
     * 更新菜谱
     * @param recipe
     * @return
     */
    boolean updateRecipe(Recipe recipe);

    /**
     * 添加菜谱图片
     * @param pic
     * @param name
     * @return
     */
    boolean addRecipePic(byte[] pic,String name);

    /**
     * 获取菜的图片
     * @param cid
     * @return
     */
    byte[] getRecipePic(Integer cid);

    Recipe getRecipeById(Integer cid);
}
