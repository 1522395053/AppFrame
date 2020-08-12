package com.ssyh.baseframe.ui;

public interface IView {
    /**
     *  显示加载
     */
    void showLoadding();

    /**
     *  隐藏加载
     */
    void hideLoadding();

    /**
     *  显示网络错误
     */
    void showNetError();

    /**
     *  隐藏网络错误
     */
    void hideNetError();

    /**
     *  显示无数据
     */
    void showNoData();

    /**
     *  隐藏无数据
     */
    void hideNoData();


}
