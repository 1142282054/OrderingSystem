package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.Menu;
import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.Recipe;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MenuItemMapper {

    Integer addMenuItem(List<Recipe> recipeList, Integer mid);

    List<MenuItem> getMenuByTime(@Param("mid") Integer mid);

    double getPriceByTime(String name, Integer mid);

}
