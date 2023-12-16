package com.example.orderingsystem.user.mapper;

import com.example.orderingsystem.user.po.User;

/**
 * @author MinZhi
 * @version 1.0
 */
public interface UserMapper {
    /**
     * 通过用户名获取user对象
     * @param user 用户信息
     * @return 用户信息
     */
    public User getUserByNickname(User user);

    /**
     * 根据用户id获取用户信息
     * @param uid 用户id
     * @return 用户信息
     */
    public User getUserById(Integer uid);
}
