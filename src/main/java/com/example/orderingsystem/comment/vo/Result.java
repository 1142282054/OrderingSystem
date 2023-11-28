package com.example.orderingsystem.comment.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author MinZhi
 * @version 1.0
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;
    private Long count;

    public static Result<Object> successful(){
        return new Result<>(0,"successful",null,null);
    }

    public static Result<Object> successful(String msg){
        return new Result<>(0,msg,null,null);
    }
    public static Result<Object> successful(Object data,Long count){
        return new Result<>(0,"successful",data,count);
    }
    public static Result<Object> fail(){
        return new Result<>(500,"fail",null,null);
    }
    public static Result<Object> fail(String msg){
        return new Result<>(500,msg,null,null);
    }

    private Result() {
    }

    private Result(Integer code, String message, T data, Long count) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
    }
}
