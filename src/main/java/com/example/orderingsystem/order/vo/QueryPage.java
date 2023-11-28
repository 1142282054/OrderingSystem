package com.example.orderingsystem.order.vo;

import com.example.orderingsystem.comment.util.FormatTimeUtil;
import com.example.orderingsystem.comment.vo.Page;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author MinZhi
 * @version 1.0
 */
@Data
public class QueryPage extends Page {
    private Integer uid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Integer isGroupBy;
}
