package com.example.orderingsystem.order.mapper;

import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.vo.QueryPage;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderMapper {
    void addOrderList(List<Order> orderList);


    List<Order> getOrders(@Param("page") QueryPage page);

    List<Order> getOrdersAndGroup(@Param("page") QueryPage page);

    Integer updateOrders(@Param("order") Order order, @Param("sid") Integer sid);
}
