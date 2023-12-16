package com.example.orderingsystem.user.service.impl;

import com.example.orderingsystem.user.mapper.TestJsonMapper;
import com.example.orderingsystem.user.mapper.UserMapper;
import com.example.orderingsystem.user.po.Init;
import com.example.orderingsystem.user.po.User;
import com.example.orderingsystem.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    TestJsonMapper testJsonMapper;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     * 登录验证
     * @param user 用户信息
     * @return
     * 1.先通过账号查询出密码，
     * 2.再进行安全性验证
     *  -成功:返回用户信息对象
     *  -失败:返回 null
     */
    @Override
    public User checkLogin(User user) {

        User desUser = userMapper.getUserByNickname(user);
        if (desUser != null &&
                passwordEncoder.matches(user.getPwd(),desUser.getPwd())){
            return desUser;
        };
        return null;
    }

    /**
     * 根据角色id从redis中获取init.json配置
     * 1.判断登录等级,获取不同key值
     * 2.根据key值来获取redeis中对相应初始化信息
     * 3.返回初始化信息
     * @param roleId
     * @return
     */
    @Override
    public String getInitJson(Integer roleId) {

        String role = "";
        switch (roleId){
            case 1:
                role  = "manager";
                break;
            case 2:
                role = "employee";
                break;
        }
        String initJson = stringRedisTemplate.opsForValue().get(role);

        return initJson;
    }

    /**
     * 从mysql中获取登录信息
     * @param role 用户权限等级
     * @return 初始化信息
     */
    @Override
    public String getInitJsonByMysql(Integer role) {
        Init init = testJsonMapper.getInit(role);

        return init.getInitList();
    }
}
