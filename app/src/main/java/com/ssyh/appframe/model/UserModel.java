package com.ssyh.appframe.model;

import com.ssyh.appframe.bean.User;

import java.util.List;

public interface UserModel {
    interface LoadUsersCallBack{
        void complete(List<User> personList);
    }

    interface GetUserCallBack{
        void complete(User user);
    }


    void loadUsers(LoadUsersCallBack loadUsersCallBack);
    void getUsers(String userId,GetUserCallBack getUserCallBack);
}
