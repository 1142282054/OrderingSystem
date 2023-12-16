package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.Menu;
import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MenuItemMapper {
    /**
     * 批量添加菜单列表
     * @param recipeList 菜单列表
     * @param mid 菜单id
     * @return 插入条数
     */
    Integer addMenuItem(List<Recipe> recipeList, Integer mid);

    /**
     * 根据菜单id获取菜列表信息
     * @param mid 菜单id
     * @return 菜列表
     */
    List<MenuItem> getMenuByTime(@Param("mid") Integer mid);

    /**
     * 获取菜价格
     * @param name
     * @param mid
     * @return
     */
    double getPrice(String name, Integer mid);

}
