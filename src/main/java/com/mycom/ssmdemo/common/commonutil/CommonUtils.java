package com.mycom.ssmdemo.common.commonutil;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 上午 10:38
 * @description： 这里是一些通过的方法
 * @modified By：
 * @version: $
 */
public class CommonUtils {

    //类加载时进行预编译，提高速度，不要放到方法内编译
    //手机号
    private static Pattern pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
    //字符串是否为数字
    private static Pattern numPattern = Pattern.compile("[0-9]*");
    /**
     * 手机号码校验
     * @param mobile
     * @return true/false
     */
    public static boolean isMobile(String mobile){
        //Pattern pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = pattern.matcher(mobile);
        boolean b = matcher.matches();
        return b;
    }

    public static boolean isNumberic(String str){
        Matcher matcher = numPattern.matcher(str);
        boolean b = matcher.matches();
        return b;
    }

    /**
     * 生成6位随机数
     * @return
     */
    public static String randomNum(){
        return new Random().nextInt(899999) + 100000 + "";
    }

    /**
     * 判断文件大小
     * len：文件实际长度（字节B）
     * size：规定的文件大小
     * unit：规定的文件大小单位
     * @return
     */
    public static boolean checkFileSize(long len, int size, String unit){
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())){
            fileSize = (double) len;
        }else if ("K".equals(unit.toUpperCase())){
            fileSize = (double) len / 1024;
        }else if ("M".equals(unit.toUpperCase())){
            fileSize = (double) len / (1024 * 1024);
        }else if ("G".equals(unit.toUpperCase())){
            fileSize = (double) len / (1024 * 1024 * 1024);
        }

        if (fileSize > size){
            return false;
        }
        return true;
    }



}
