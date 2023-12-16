package com.example.orderingsystem.recipe.service.impl;


import com.example.orderingsystem.comment.util.FormatTimeUtil;
import com.example.orderingsystem.recipe.mapper.MenuItemMapper;
import com.example.orderingsystem.recipe.mapper.MenuMapper;
import com.example.orderingsystem.recipe.mapper.TmpOrderMapper;
import com.example.orderingsystem.recipe.po.Menu;
import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.po.TmpOrder;
import com.example.orderingsystem.recipe.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper menuMapper;

    @Resource
    MenuItemMapper menuItemMapper;

    @Resource
    TmpOrderMapper tmpOrderMapper;

    /**
     * 添加菜单
     * 1.查看今日是否已有菜单
     *  -有,返回null
     *  -没有,继续
     * 2.创建菜单,存入今日的时间
     * 3.添加表单,返回的menu包含id值
     * 4.返回id值
     * @return id值
     * @throws ParseException
     */
    @Override
    public Integer addMenu() throws ParseException {
        Menu menu =  menuMapper.getMenu(FormatTimeUtil.getDate());
        if (menu == null){
            menu = new Menu();
            menu.setCtime(FormatTimeUtil.getDate());
            menuMapper.addMenu(menu);
            Integer mid = menu.getMid();
            log.info(mid.toString());
            return mid;
        }
        return null;
    }

    /**
     * 添加菜单列表
     * @param recipeList 菜单列表
     * @param mid 菜单id
     * @return
     */
    @Override
    public boolean addMenuItems(List<Recipe> recipeList, Integer mid) {
        Integer add = menuItemMapper.addMenuItem(recipeList,mid);
        if (add > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取今日的菜单列表
     * 1.查看今日的菜单id
     *  -存在,继续
     *  -失败,返回null
     * 2.根据菜单id来获取今日菜单列表
     * 3.返回菜单列表
     * @return 菜单列表
     * @throws ParseException
     */
    @Override
    public List<MenuItem> getMenuByDate() throws ParseException {
        Menu menu = menuMapper.getMenu(FormatTimeUtil.getDate());
        List<MenuItem> menuItems = null;
        if (menu != null){
            menuItems  = menuItemMapper.getMenuByTime(menu.getMid());
        }
        return menuItems;
    }

    /**
     * 获取用户临时点餐表
     * @param uid 用户id
     * @return 用户临时点餐表
     */
    @Override
    public List<TmpOrder> getTmpOrder(Integer uid) {
        List<TmpOrder> tmpOrderList = tmpOrderMapper.getTmpOrder(uid);
        return tmpOrderList;
    }

    /**
     * 更新用户临时点餐表
     * 1.根据菜名与用户id查找临时点餐表中的订单信息
     *  -结果为null
     *      插入记录:
     *       1)根据今日时间获取菜单id
     *       2)根据菜单id与菜名获取价格
     *       3)插入临时表
     *  -不为null
     *      -数量>0 更新数据
     *      -数量-0 删除数据
     * @param tmpOrder 更新的菜的信息
     * @param uid 用户id
     * @return
     * @throws ParseException
     */
    @Override
    public boolean changeTmpOrder(TmpOrder tmpOrder, Integer uid) throws ParseException {
//       根据菜的name与uid去tmp表获取信息，
        TmpOrder checkOrder = tmpOrderMapper.getTmpOrderByName(tmpOrder.getName(),uid);
//          （1）如果为空，获得菜的价格,插入数据
        if (checkOrder == null){
            Menu menu = menuMapper.getMenu(FormatTimeUtil.getDate());
            double price = menuItemMapper.getPrice(tmpOrder.getName(),menu.getMid());
            tmpOrderMapper.addTmpOrder(tmpOrder,uid,price);
            return true;
        }

//          （2）如果不为空
//                且大于>0，更新数据
//                  等于0，删除数据
        if (tmpOrder.getNumber() > 0){
            tmpOrderMapper.updateOrder(tmpOrder,uid);
            return true;
        }
        if (tmpOrder.getNumber() == 0){
            tmpOrderMapper.deleteOrder(tmpOrder,uid);
            return true;
        }
        return false;
    }

    /**
     * 删除临时点餐表
     * @param uid 用户id
     */
    @Override
    public void deleteTmpOrder(Integer uid) {
        tmpOrderMapper.deleteOrderById(uid);
    }
}
