package com.ssyh.appframe.bean;

import androidx.databinding.Bindable;

public class User {
    public String id;
    @Bindable
    public String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
