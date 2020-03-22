package com.mycom.ssmdemo.util;

import com.mycom.ssmdemo.utiltest.bean.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：songdalin
 * @date ：Created in 2020-03-22 下午 02:18
 * @description：
 * @modified By：
 * @version:
 */
class ObjectUtilsTest {

    @Test
    void isObjectFieldEmpty() {

        ObjectUtils objectUtils = new ObjectUtils();
        Food food = new Food();
        //重量是空的
        food.setName("米多奇膜片");
        food.setBrand("米多奇");
        food.setPrice(30.2);
        //food.setWeight("20");

        assertEquals(true, objectUtils.isObjectFieldEmpty(food));
    }
}