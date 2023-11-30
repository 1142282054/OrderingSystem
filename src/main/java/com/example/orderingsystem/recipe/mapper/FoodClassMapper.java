package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.FoodClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodClassMapper {
    FoodClass getClassById(@Param("id") Integer id);

    List<FoodClass> getFoodClassList();
}
