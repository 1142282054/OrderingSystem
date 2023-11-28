package com.example.orderingsystem.user.po;


import lombok.Data;

@Data
public class User {

  private Integer uid;
  private String loginName;
  private String pwd;
  private String nickname;
  private String phone;
  private Integer dept;
  private Integer role;
}
