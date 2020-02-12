package com.mycom.ssmdemo.util;

//import java.util.logging.Logger;

import org.apache.log4j.Logger;

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
            logger = Logger.getLogger("SsmdemoApplication");
        }
        return logger;
    }
}
