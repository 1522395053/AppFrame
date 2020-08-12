package com.ssyh.appframe;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssyh.appframe.bean.User;
import com.ssyh.appframe.present.UserPresent;
import com.ssyh.appframe.test.adapter.SimpleAdapter;
import com.ssyh.appframe.ui.UserView;
import com.ssyh.baseframe.ui.activity.BaseActivityV2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivityV2<UserView, UserPresent> implements UserView {

    private TextView tv_user;
    private RecyclerView rv_strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_user = findViewById(R.id.tv_user);
        rv_strings = findViewById(R.id.rv_strings);
        mPresent.fetch();
    }

    @Override
    protected int getContentViewXmlId() {
        return R.layout.activity_main;
    }

    @Override
    protected UserPresent createPresent() {
        return new UserPresent();
    }

    @Override
    public void showUsers(List<User> users) {
        if (null != users && users.size() > 0){
            tv_user.setText(users.get(0).name);
        }
        rv_strings.setLayoutManager(new LinearLayoutManager(this));
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("第"+i+"个item");
        }
        rv_strings.setAdapter(new SimpleAdapter(this,stringList));
    }

    @Override
    public void showLoadding() {
        Toast.makeText(this,"拼命加载中啊啊啊~~~~",Toast.LENGTH_LONG).show();
    }
}
