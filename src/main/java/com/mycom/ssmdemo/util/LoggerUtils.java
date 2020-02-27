package com.mycom.ssmdemo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-12 下午 05:03
 * @description：rizhiceshi
 * @modified By：
 * @version: $
 */
public class LoggerUtils {
    private static Logger logger;

    private LoggerUtils(){}

    public static Logger getLogger(){
        if (logger == null){
            logger = LoggerFactory.getLogger(LoggerUtils.class);
        }
        return logger;
    }
}
