package com.example.orderingsystem.recipe.service;

import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.po.TmpOrder;

import java.text.ParseException;
import java.util.List;

public interface MenuService {
    Integer addMenu() throws ParseException;

    boolean addMenuItems(List<Recipe> recipeList, Integer mid);

    List<MenuItem> getMenuByDate() throws ParseException;

    List<TmpOrder> getTmpOrder(Integer uid);

    boolean changeTmpOrder(TmpOrder tmpOrder, Integer uid) throws ParseException;

    void deleteTmpOrder(Integer uid);
}
