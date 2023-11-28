package com.example.orderingsystem.user.po;

import lombok.Data;

@Data
public class Init {

  private long rid;
  private String initList;


  public long getRid() {
    return rid;
  }

  public void setRid(long rid) {
    this.rid = rid;
  }


  public String getInitList() {
    return initList;
  }

  public void setInitList(String initList) {
    this.initList = initList;
  }

}
