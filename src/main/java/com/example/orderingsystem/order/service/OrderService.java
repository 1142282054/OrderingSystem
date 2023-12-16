package com.example.orderingsystem.order.service;

import com.example.orderingsystem.comment.vo.Page;
import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.vo.QueryPage;
import com.example.orderingsystem.recipe.po.TmpOrder;
import com.example.orderingsystem.user.po.Send;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
public interface OrderService {

    /**
     * 批量添加
     * @param tmpOrderList 订单信息
     * @param uid 用户id
     * @throws ParseException
     */
    void addOrderList(List<TmpOrder> tmpOrderList, Integer uid) throws ParseException;

    /**
     * 根据查询条件获取订单列表
     * @param page 查询条件
     * @return 订单列表
     */
    List<Order> getOrderList(QueryPage page);

    /**
     * 添加配送信息
     * @param uid 配送员id
     * @return 配送id
     * @throws ParseException 时间转换异常
     */
    Send addSend(Integer uid) throws ParseException;

    /**
     * 更新订单配送信息
     * @param order 订单信息
     * @param send 配送id
     * @return 更新标志
     */
    boolean commitOrder(Order order, Send send);

    /**
     * 统计订单数量
     * @return 订单总数
     */
    Integer countOrder(QueryPage page);
}
