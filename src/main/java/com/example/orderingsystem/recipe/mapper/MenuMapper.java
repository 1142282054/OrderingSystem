package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface MenuMapper {
    Menu getMenu(@Param("date")Date date);

    Integer addMenu(Menu menu);
}
