package com.example.orderingsystem.order.service.impl;

import com.example.orderingsystem.comment.util.FormatTimeUtil;
import com.example.orderingsystem.comment.vo.Page;
import com.example.orderingsystem.order.mapper.OrderMapper;
import com.example.orderingsystem.order.mapper.SendMapper;
import com.example.orderingsystem.order.po.Order;
import com.example.orderingsystem.order.service.OrderService;
import com.example.orderingsystem.order.vo.QueryPage;
import com.example.orderingsystem.recipe.mapper.MenuMapper;
import com.example.orderingsystem.recipe.po.TmpOrder;
import com.example.orderingsystem.user.po.Send;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MinZhi
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    MenuMapper menuMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    SendMapper sendMapper;

    public void addOrderList(List<TmpOrder> tmpOrderList, Integer uid) throws ParseException {
//        把tmpOrder转成Oder
        List<Order> orderList = new ArrayList<>();
        //        name,number tmpOrder
        //        mid,从时间 menu找
        //        uid session获取
        Date date = FormatTimeUtil.getDate();
        Integer mid = menuMapper.getMenu(date).getMid();
        tmpOrderList.forEach(tmpOrder -> {
            Order order = new Order();
            order.setName(tmpOrder.getName());
            order.setNumber(tmpOrder.getNumber());
            order.setMid(mid);
            order.setUid(uid);
            order.setCtime(date);
            orderList.add(order);
        });

        orderMapper.addOrderList(orderList);

    }

    @Override
    public List<Order> getOrderList(QueryPage page) {
        List<Order> orderList = null;
        if (page.getIsGroupBy() != null && page.getIsGroupBy() == 1){
            orderList = orderMapper.getOrdersAndGroup(page);
        }else {
            orderList = orderMapper.getOrders(page);
        }
        return orderList;
    }

    @Override
    public Send addSend(Integer uid) throws ParseException {
        Send send = new Send();
        send.setUid(uid);
        send.setDeliver(1);
        send.setDtime(FormatTimeUtil.getDate());

        sendMapper.addSend(send);
        return send;
    }

    @Override
    public boolean commitOrder(Order order, Send send) {
        Integer update = orderMapper.updateOrders(order,send.getSid());
        if (update > 0){
            return true;
        }
        return false;
    }
}
