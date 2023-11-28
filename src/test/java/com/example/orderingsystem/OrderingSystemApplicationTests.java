package com.example.orderingsystem;

import com.example.orderingsystem.user.po.Init;
import com.example.orderingsystem.user.mapper.TestJsonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class OrderingSystemApplicationTests {

    @Resource
    RedisTemplate redisTemplate;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {

//        String tabList2 = (String) redisTemplate.opsForValue().get("tabList2");
//
//        System.out.println(tabList2);

//        redisTemplate.opsForValue().set("test01",new String("sasasasas"));

        redisTemplate.opsForHash().put("initList", "manager","abc");
//        System.out.println(init);

//        String tabList21 = stringRedisTemplate.opsForValue().get("tabList2");
//        System.out.println(tabList21);

    }
    class Car {
        private String brand = null;
        private int doors = 0;

        public String getBrand() { return this.brand; }
        public void   setBrand(String brand){ this.brand = brand;}

        public int  getDoors() { return this.doors; }
        public void setDoors (int doors) { this.doors = doors; }
    }

    @Test
    void test02() throws IOException {
//        JSONObject
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);

        objectMapper.writeValue(
                new FileOutputStream("src\\test\\java\\com\\example\\orderingsystem\\aa.json"), car);

    }

    @Resource
    TestJsonMapper testJsonMapper1;

    @Test
    void test03(){
        Init init = testJsonMapper1.getInit();
        System.out.println(init);
    }

}
