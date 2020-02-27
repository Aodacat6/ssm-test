package com.mycom.ssmdemo.common.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-11 下午 08:26
 * @description：通用返回方法类
 * @modified By：
 * @version: 1.0.0$
 */
public class ResponseData {

    private int code;

    private String msg;

    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    //list返回json对应的是[]
    private List data = new ArrayList();
    //map返回json对应的是{}
    private Map<String,Object> meta = new HashMap<>();

    private ResponseData putData(String key, Object value){
        this.meta.put(key, value);
        return this;
    }
    private ResponseData (int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    private ResponseData(int code, String msg, long count) {
        this.code = code;
        this.msg = msg;
        this.count = count;
    }
    public static ResponseData okData(String key, Object value){

        ResponseData data = new ResponseData(0,"ok");
        data.putData(key, value);
        return data;
    }

    public static ResponseData okData(List value, long count) {
        ResponseData data = new ResponseData(0, "ok", count);
        data.putData(value);
        return data;
    }

    public static ResponseData ok(){
        return new ResponseData(0, "ok");
    }
    public static ResponseData error(){
        return new ResponseData(1,"error");
    }
    public static ResponseData error(String msg){
        return new ResponseData(1, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public ResponseData putData(List value) {
        data.addAll(value);
        return this;
    }
}
