package com.example.orderingsystem.recipe.po;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Menu {

  private Integer mid;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date ctime;
  private String info;



}
