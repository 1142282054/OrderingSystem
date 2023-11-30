package com.example.orderingsystem.recipe.po;

import lombok.Data;

@Data
public class MenuItem {

  private Integer id;
  private Integer cid;
  private Integer mid;
  private String name;
  private byte[] photo;
  private String unit;
  private Integer classId;
  private double price;
  private String info;

  private FoodClass foodClass;


}
