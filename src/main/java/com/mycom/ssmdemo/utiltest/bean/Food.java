package com.mycom.ssmdemo.utiltest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：songdalin
 * @date ：Created in 2020-03-22 上午 10:51
 * @description：
 * @modified By：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private String name;

    private String weight;

    private double price;

    public String brand;
}
