package com.ssyh.appframe;


import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssyh.appframe.bean.User;
import com.ssyh.appframe.present.UserPresent;
import com.ssyh.appframe.test.adapter.SimpleAdapter;
import com.ssyh.appframe.test.hotfix.HotFixEngine;
import com.ssyh.appframe.test.hotfix.TestClass;
import com.ssyh.appframe.ui.UserView;
import com.ssyh.baseframe.ui.activity.BaseActivityV2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivityV2<UserView, UserPresent> implements UserView,View.OnClickListener{

    private TextView tv_user;
    private TextView tv_create_bug;
    private TextView tv_fix_bug;
    private RecyclerView rv_strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_user = findViewById(R.id.tv_user);
        rv_strings = findViewById(R.id.rv_strings);
        mPresent.fetch();

        tv_create_bug = findViewById(R.id.tv_create_bug);
        tv_fix_bug = findViewById(R.id.tv_fix_bug);
        tv_create_bug.setOnClickListener(this);
        tv_fix_bug.setOnClickListener(this);
        if (android.os.Build.VERSION.SDK_INT >= 23){
            requestPermissions(
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    102);
        }


    }






    /**
     * 关于dex文件被恶意加载和替换的解决方案
     * 1.可通过在服务器生成一个dex文件的MD5列表，在修复之前客户端
     * 向服务发送验证请求,验证通过即可修复。
     * 2.将dex文件打包为rar并且设置密码，在客户端通过ndk进行验证解密
     * @param view
     */
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_fix_bug:
                HotFixEngine.copyDexFileToAppAndFix(this,"classes.dex",true);
                break;
            case R.id.tv_create_bug:
                new TestClass().showToast(null,getApplication());
                break;
        }
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
