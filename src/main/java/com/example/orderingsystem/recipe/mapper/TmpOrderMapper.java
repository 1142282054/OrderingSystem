package com.example.orderingsystem.recipe.mapper;

import com.example.orderingsystem.recipe.po.TmpOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TmpOrderMapper {
    /**
     * 根据用户id获取临时点餐表
     * @param uid 用户id
     * @return
     */
    List<TmpOrder> getTmpOrder(Integer uid);

    /**
     * 通过用户id与菜名获取临时点餐表中菜的信息
     * @param name 菜名
     * @param uid 用户id
     * @return
     */
    TmpOrder getTmpOrderByName(String name, Integer uid);

    /**
     * 添加
     * @param tmpOrder
     * @param uid
     * @param price
     */
    void addTmpOrder(@Param("tmpOrder")TmpOrder tmpOrder, Integer uid, double price);

    /**
     * 更新
     * @param tmpOrder
     * @param uid
     */
    void updateOrder(@Param("tmpOrder") TmpOrder tmpOrder, Integer uid);

    /**
     * 批量删除
     * @param tmpOrder
     * @param uid
     */
    void deleteOrder(@Param("tmpOrder")TmpOrder tmpOrder, Integer uid);

    /**
     * 删除
     * @param uid
     */
    void deleteOrderById(Integer uid);
}
