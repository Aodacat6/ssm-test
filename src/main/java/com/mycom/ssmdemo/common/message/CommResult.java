package com.mycom.ssmdemo.common.message;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-20 上午 09:46
 * @description：
 * @modified By：
 * @version: $
 */
public class CommResult<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    private CommResult(T data){

    }

    private CommResult(String msg){

    }

    //static<T> 将方法设为泛型方法
    public static<T> CommResult<T> ok (T data){
        CommResult<T> commResult = new CommResult<>(data);
        commResult.code = 0;
        commResult.msg = "ok";
        commResult.data = data;

        return commResult;
    }

    public static <T> CommResult<T> error(String msg) {
        CommResult<T> result = new CommResult<>(msg);
        result.code = -1;
        result.msg = msg;
        return result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
