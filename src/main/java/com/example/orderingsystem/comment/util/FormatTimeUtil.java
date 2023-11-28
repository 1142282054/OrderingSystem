package com.example.orderingsystem.comment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author MinZhi
 * @version 1.0
 */
public class FormatTimeUtil {

    public static Date getMonth(Date date){
        //创建Calendar实例
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        cal.add(Calendar.MONTH, 1);  //在当前时间基础上加一年

        Date date1 = cal.getTime();
        return date1;
    }

    public static Date getDate() throws ParseException {

        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
        return temp.parse(temp.format(new Date()));

    }
}
