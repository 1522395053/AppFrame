package com.ssyh.appframe.ui;

import com.ssyh.appframe.bean.User;
import com.ssyh.baseframe.ui.IView;

import java.util.List;

public interface UserView extends IView {
    void showUsers(List<User> users);
}
