package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MenuMapper {
    /**
     * 根据日期获取菜单信息
     * @param date
     * @return 菜单信息
     */
    Menu getMenu(@Param("date")Date date);

    /**
     * 插入menu,获取主键id值
     * @param menu menu,包含时间
     * @return
     */
    Integer addMenu(Menu menu);
}
