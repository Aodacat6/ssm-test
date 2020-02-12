package com.mycom.ssmdemo.common.commexception;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-11 下午 06:19
 * @description：自定义错误类型
 * @modified By：
 * @version: 1.1.0$
 */
public class BizException extends RuntimeException{
    private int code;

    public BizException(String errMsg){
        super(errMsg);
        this.code = 1;
    }

    public BizException(String errMsg, int errCode){
        super(errMsg);
        this.code = errCode;
    }
}
