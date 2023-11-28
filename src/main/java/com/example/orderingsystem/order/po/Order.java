package com.example.orderingsystem.order.po;

import com.example.orderingsystem.recipe.po.MenuItem;
import com.example.orderingsystem.user.po.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Order {

  private Integer id;
  private Integer uid;
  private String name;
  private Integer mid;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date ctime;
  private Integer number;
  private Integer sid;

  private User user;
  private Double price;
}
