package com.example.orderingsystem.user.service;

import com.example.orderingsystem.user.po.User;

public interface UserService {
    User checkLogin(User user);

    String getInitJson(Integer role);

    String getInitJsonByMysql(Integer role);
}
