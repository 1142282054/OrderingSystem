package com.example.orderingsystem.recipe.service;

import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.po.TmpOrder;

import java.text.ParseException;
import java.util.List;

public interface MenuService {
    /**
     * 添加菜单
     * @return 菜单的id值
     * @throws ParseException
     */
    Integer addMenu() throws ParseException;

    /**
     * 添加菜单列表
     * @param recipeList 菜单列表
     * @param mid 菜单id
     * @return 添加标志
     */
    boolean addMenuItems(List<Recipe> recipeList, Integer mid);

    /**
     * 根据今日时间获取菜单列表
     * @return 菜单列表
     * @throws ParseException
     */
    List<MenuItem> getMenuByDate() throws ParseException;

    /**
     * 根据用户id获取临时点餐表的信息
     * @param uid 用户id
     * @return 临时点餐表信息
     */
    List<TmpOrder> getTmpOrder(Integer uid);

    /**
     * 更新临时点餐表
     * @param tmpOrder 更新的菜的信息
     * @param uid 用户id
     * @return 更新标志
     * @throws ParseException
     */
    boolean changeTmpOrder(TmpOrder tmpOrder, Integer uid) throws ParseException;

    /**
     * 删除用户临时点餐表
     * @param uid 用户id
     */
    void deleteTmpOrder(Integer uid);
}
