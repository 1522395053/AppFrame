package com.ssyh.baseframe.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssyh.baseframe.bean.EventBusMsg;
import com.ssyh.baseframe.present.BasePresent;
import com.ssyh.baseframe.ui.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity<V,T extends BasePresent<V>> extends AppCompatActivity implements IView {

    protected T mPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mPresent = createPresent();
        mPresent.attachView((V) this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventBusMsg eventBusMsg) {
        if (null == eventBusMsg)
            return;
        switch (eventBusMsg.code){
            case 1:
                showLoadding();
                break;
            case 10:
                hideLoadding();
                break;
        }

    }

    protected abstract T createPresent();

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (null != mPresent){
            mPresent.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoadding() {
        Toast.makeText(this,"加载中",Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoadding() {
        Toast.makeText(this,"加载完毕",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void hideNetError() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void hideNoData() {

    }

}
