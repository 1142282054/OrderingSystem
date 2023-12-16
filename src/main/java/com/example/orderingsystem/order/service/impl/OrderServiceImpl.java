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

    /**
     * 批量添加订单信息
     * 1.把tmpOrder转成order:
     *      1)name:tmp获取
     *      2)time:Date转换
     *      3)uid:session获取
     *      4)mid:menuMapper查找
     * 2.批量插入order表
     * @param tmpOrderList 订单信息
     * @param uid 用户id
     * @throws ParseException
     */
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

    /**
     * 根基查询条件获取order列表
     * 1.查看是否分组
     *  -分组:    分组查询,获取菜名\数量\价格
     *  -不分组:  根据条件获取全部信息
     * 2.返回查询信息
     * @param page 查询条件
     * @return 订单列表
     */
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

    /**
     * 添加配送记录
     * 1.创建一个并初始化一个配送信息
     * 2.插入信息并获取配送id
     * 3.返回配送id
     * @param uid 配送员id
     * @return 配送id
     * @throws ParseException
     */
    @Override
    public Send addSend(Integer uid) throws ParseException {
        Send send = new Send();
        send.setUid(uid);
        send.setDeliver(1);
        send.setDtime(FormatTimeUtil.getDate());

        sendMapper.addSend(send);
        return send;
    }

    /**
     * 更新订单的配送状态
     * @param order 订单信息
     * @param send 配送id
     * @return
     */
    @Override
    public boolean commitOrder(Order order, Send send) {
        Integer update = orderMapper.updateOrders(order,send.getSid());
        if (update > 0){
            return true;
        }
        return false;
    }

    /**
     * 统计数量
     * @param page 限制条件
     * @return
     */
    @Override
    public Integer countOrder(QueryPage page) {

        Integer count = null;
        if (page.getIsGroupBy() != null && page.getIsGroupBy() == 1){
            count = orderMapper.countOrderByGroup(page);
        }else {
            count = orderMapper.countOrder(page);
        }
//        Integer count = orderMapper.countOrder(page);
        return count;
    }
}
