package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.TmpOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmpOrderMapper {
    List<TmpOrder> getTmpOrder(Integer uid);

    TmpOrder getTmpOrderByName(String name, Integer uid);

    void addTmpOrder(@Param("tmpOrder")TmpOrder tmpOrder, Integer uid, double price);

    void updateOrder(@Param("tmpOrder") TmpOrder tmpOrder, Integer uid);

    void deleteOrder(@Param("tmpOrder")TmpOrder tmpOrder, Integer uid);

    void deleteOrderById(Integer uid);
}
