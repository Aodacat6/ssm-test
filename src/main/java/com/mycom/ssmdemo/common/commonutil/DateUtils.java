package com.mycom.ssmdemo.common.commonutil;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 下午 01:59
 * @description：
 * @modified By：
 * @version: $
 */
public class DateUtils {

    public static String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
}
