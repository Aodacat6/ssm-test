package com.mycom.ssmdemo.util;

import com.mycom.ssmdemo.utiltest.bean.Food;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author ：songdalin
 * @date ：Created in 2020-03-22 上午 10:39
 * @description：对象操作工具类
 * @modified By：
 * @version: 1.0.0.0
 */
public class ObjectUtils {

    /**
     * 根据传入的对象实例和类名，判断对象是否有参数
     * @param obj
     * @return true: 如果类属性存在空 false:类属性全都不为空
     */
    public static boolean isObjectFieldEmpty(Object obj){

        boolean isEmpty = false;
        Class<?> tClass = obj.getClass();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            try {
                Object object = field.get(obj);
                if (object == null || "".equals(object)){
                    isEmpty = true;
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return isEmpty;
    }

    /**
     * 反射机制测试
     * @param args
     */
/*    public static void main(String[] args){
        //通过对象获得类信息
        Food food = new Food();
        //重量是空的
        food.setName("米多奇膜片");
        food.setBrand("米多奇");
        food.setPrice(30.2);
        Field[] fields = Food.class.getDeclaredFields();

        for (Field field : fields){
            try {
                field.setAccessible(true);
                Object obj = field.get(food);
                if (obj == null || "".equals(obj)) {
                    System.out.println("youkongzhi：" + field.getName());
                    break;
                };
                //System.out.println(field.get(food).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //System.out.println(Arrays.toString(fields));
    }*/
}
