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

    @Override
    public boolean addMenuItems(List<Recipe> recipeList, Integer mid) {
        Integer add = menuItemMapper.addMenuItem(recipeList,mid);
        if (add > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<MenuItem> getMenuByDate() throws ParseException {
        Menu menu = menuMapper.getMenu(FormatTimeUtil.getDate());
        List<MenuItem> menuItems = null;
        if (menu != null){
            menuItems  = menuItemMapper.getMenuByTime(menu.getMid());
        }
        return menuItems;
    }

    @Override
    public List<TmpOrder> getTmpOrder(Integer uid) {
        List<TmpOrder> tmpOrderList = tmpOrderMapper.getTmpOrder(uid);
        return tmpOrderList;
    }

    @Override
    public boolean changeTmpOrder(TmpOrder tmpOrder, Integer uid) throws ParseException {
//       根据菜的name与uid去tmp表获取信息，
        TmpOrder checkOrder = tmpOrderMapper.getTmpOrderByName(tmpOrder.getName(),uid);
//          （1）如果为空，获得菜的价格,插入数据
        if (checkOrder == null){
            Menu menu = menuMapper.getMenu(FormatTimeUtil.getDate());
            double price = menuItemMapper.getPriceByTime(tmpOrder.getName(),menu.getMid());
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

    @Override
    public void deleteTmpOrder(Integer uid) {
        tmpOrderMapper.deleteOrderById(uid);
    }
}
