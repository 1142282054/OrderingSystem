package com.example.orderingsystem.order.mapper;

import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.vo.QueryPage;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderMapper {
    /**
     * 批量添加
     * @param orderList
     */
    void addOrderList(List<Order> orderList);

    /**
     * 条件查询
     * @param page
     * @return
     */
    List<Order> getOrders(@Param("page") QueryPage page);

    /**
     * 分组查询
     * @param page
     * @return
     */
    List<Order> getOrdersAndGroup(@Param("page") QueryPage page);

    /**
     * 更新订单配送状态
     * @param order
     * @param sid
     * @return
     */
    Integer updateOrders(@Param("order") Order order, @Param("sid") Integer sid);

    /**
     * 统计数量
     * @param page 限制条件
     * @return
     */
    Integer countOrder(@Param("page")QueryPage page);

    /**
     * 统计分组数量
     * @param page
     * @return
     */
    Integer countOrderByGroup(@Param("page")QueryPage page);
}
