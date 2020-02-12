package com.mycom.ssmdemo.cotroller.vip;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-12 下午 03:19
 * @description：q
 * @modified By：
 * @version: $
 */
public class Test {
    public static  void sort(int[] arr) {
        int temp;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println();
    }
    public static void  main(String args[]){
        int[] arr = {3,4,1,5,2};
        Test.sort(arr);
    }

}