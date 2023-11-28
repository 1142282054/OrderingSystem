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

    void addOrderList(List<TmpOrder> tmpOrderList, Integer uid) throws ParseException;

    List<Order> getOrderList(QueryPage page);

    Send addSend(Integer uid) throws ParseException;

    boolean commitOrder(Order order, Send send);
}
