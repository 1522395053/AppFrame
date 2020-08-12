package com.ssyh.baseframe.net;

public class RspModel<T> {

    public final static int SERVER_CODE_SUCCESS = 1;//成功
    public final static int SERVER_CODE_NOT_LOGIN = 2002;//未登录
    public final static int SERVER_CODE_IN_OTHER_LOGIN = 2003;//在其他设备上登录
    public final static int SERVER_CODE_NOT_GROUP_HEAD = 10007;//被取消团长身份/不是团长


    private int code;
    private String msg;
    private T data;
    public boolean refresh_task_buoy;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
