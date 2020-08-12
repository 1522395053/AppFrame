package com.ssyh.baseframe.bean;

public class EventBusMsg {
    public int code;
    public boolean booleanData;

    public EventBusMsg(int code) {
        this.code = code;
    }

    public EventBusMsg(int code, boolean booleanData) {
        this.code = code;
        this.booleanData = booleanData;
    }
}
