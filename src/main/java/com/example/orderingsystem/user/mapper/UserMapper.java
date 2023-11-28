package com.example.orderingsystem.user.mapper;

import com.example.orderingsystem.user.po.User;

/**
 * @author MinZhi
 * @version 1.0
 */
public interface UserMapper {
    /**
     * 通过用户名获取user对象
     * @param user
     * @return
     */
    public User getUserByNickname(User user);

    public User getUserById(Integer uid);
}
