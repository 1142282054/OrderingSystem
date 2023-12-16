package com.example.orderingsystem.comment.exception;


import com.example.orderingsystem.comment.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MinZhi
 * @version 1.0
 */
//@ControllerAdvice
public class GlobalException {

    /**
     * 全局异常处理
     * @param e 异常
     * @return 错误信息的json
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result globalHandler(Exception e){
        return Result.fail("系统错误：" + e.getMessage());
    }
}
