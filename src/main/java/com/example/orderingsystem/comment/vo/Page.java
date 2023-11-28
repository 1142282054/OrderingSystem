package com.example.orderingsystem.comment.vo;

import lombok.Data;

/**
 * @author MinZhi
 * @version 1.0
 */
@Data
public class Page {
    private Integer page;
    private Integer limit;

    public Integer getStart(){
        return (page-1)*limit;
    }
}
