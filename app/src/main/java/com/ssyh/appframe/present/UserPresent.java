package com.ssyh.appframe.present;

import com.ssyh.appframe.bean.User;
import com.ssyh.appframe.model.UserModel;
import com.ssyh.appframe.model.impl.UserModelImpV1;
import com.ssyh.baseframe.present.BasePresent;
import com.ssyh.appframe.ui.UserView;

import java.util.List;

public class UserPresent extends BasePresent<UserView> {

    private UserModel userModel = new UserModelImpV1();

    public void fetch(){
        if (null != getView()){
            getView().showLoadding();
        }
        userModel.loadUsers(new UserModel.LoadUsersCallBack() {
            @Override
            public void complete(List<User> users) {
                if (null != getView()){
                    getView().showUsers(users);
                    getView().hideLoadding();
                }
            }
        });
    }
}
