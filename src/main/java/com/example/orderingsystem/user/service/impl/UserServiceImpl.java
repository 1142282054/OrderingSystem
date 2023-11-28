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
     * @param user
     * @return
     * 先通过账号查询出密码，
     * 再进行安全性验证
     * 成功返回对象，失败返回 null
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

    @Override
    public String getInitJsonByMysql(Integer role) {
        Init init = testJsonMapper.getInit();
        return init.getInitList();
    }
}
