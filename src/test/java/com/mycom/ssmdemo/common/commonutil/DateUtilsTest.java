package com.mycom.ssmdemo.common.commonutil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 下午 02:02
 * @description：
 * @modified By：
 * @version: $
 */
class DateUtilsTest {

    @Test
    void getDate() {
        DateUtils dateUtils = new DateUtils();
        System.out.println(dateUtils.getDate());
    }
}