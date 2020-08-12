package com.ssyh.baseframe.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssyh.baseframe.R;
import com.ssyh.baseframe.present.BasePresent;
import com.ssyh.baseframe.ui.IView;

/**
 *
 * @param <V> view 接口
 * @param <T> present
 */

public  abstract class BaseActivityV2<V,T extends BasePresent<V>> extends AppCompatActivity implements IView {

    protected T mPresent;
    protected RelativeLayout rl_title_contanier;
    protected FrameLayout fl_content_contanier;
    protected TextView tv_title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);
        rl_title_contanier = findViewById(R.id.rl_title_contanier);
        fl_content_contanier = findViewById(R.id.fl_content_contanier);
        tv_title = findViewById(R.id.tv_title);
        View inflate = View.inflate(this, getContentViewXmlId(), null);
        fl_content_contanier.addView(inflate);
        if (cutsomTitle()){
            rl_title_contanier.setVisibility(View.GONE);
        }
        mPresent = createPresent();
        mPresent.attachView((V) this);
    }

    protected void setTitle(String title){
        tv_title.setText(title);
    }

    protected abstract int getContentViewXmlId();
    protected boolean cutsomTitle(){
        return false;
    }

    protected abstract T createPresent();


    @Override
    protected void onDestroy() {
        if (null != mPresent){
            mPresent.detachView();
        }
        super.onDestroy();
    }


    public void showLoadding() {
        Toast.makeText(this,"加载中",Toast.LENGTH_LONG).show();
    }


    public void hideLoadding() {
        Toast.makeText(this,"加载完毕-父类",Toast.LENGTH_LONG).show();
    }


    public void showNetError() {

    }


    public void hideNetError() {

    }


    public void showNoData() {

    }


    public void hideNoData() {

    }


}
