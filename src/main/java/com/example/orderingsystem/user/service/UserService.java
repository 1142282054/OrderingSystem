package com.example.orderingsystem.user.service;

import com.example.orderingsystem.user.po.User;

public interface UserService {
    /**
     * 登录功能
     * @param user
     * @return
     */
    User checkLogin(User user);

    /**
     * 从Redis中获取初始化信息
     * @param role
     * @return
     */
    String getInitJson(Integer role);

    /**
     * 重mysql中获取登录信息
     * @param role
     * @return
     */
    String getInitJsonByMysql(Integer role);
}
