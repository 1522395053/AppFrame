package com.ssyh.appframe.model.impl;

import android.os.Handler;
import android.os.Looper;

import com.ssyh.appframe.bean.User;
import com.ssyh.appframe.model.UserModel;
import com.ssyh.baseframe.bean.EventBusMsg;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class UserModelImpV1 implements UserModel {

    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    public void loadUsers(final LoadUsersCallBack loadUsersCallBack) {
        //真实代码情况是
        EventBus.getDefault().post(new EventBusMsg(1));
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (null != loadUsersCallBack){
                            List userlist = new ArrayList<User>();
                            userlist.add(new User("1","小明"));
                            loadUsersCallBack.complete(userlist);
                            EventBus.getDefault().post(new EventBusMsg(10));
                        }
                    }
                },3000);
            }
        }).start();

    }

    @Override
    public void getUsers(String userId, final GetUserCallBack getUserCallBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (null != getUserCallBack){
                            getUserCallBack.complete(new User("2","小华"));
                        }
                    }
                },3000);
            }
        }).start();

    }
}
