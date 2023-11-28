package com.example.orderingsystem.recipe.po;

import lombok.Data;

@Data
public class Recipe {

  private Integer cid;
  private String name;
  private String photo;
  private String unit;
  private Integer classId;
  private double price;
  private String info;

  private FoodClass foodClass;

}
