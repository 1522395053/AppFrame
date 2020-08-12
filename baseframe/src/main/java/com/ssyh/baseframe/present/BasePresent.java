package com.ssyh.baseframe.present;

import java.lang.ref.WeakReference;

public abstract class BasePresent<T> {
    public WeakReference<T> mViewRef;

    public void attachView(T ui){
        mViewRef = new WeakReference<T>(ui);
    }

    public void detachView(){
        if (null != mViewRef){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public T getView() {
        if (null != mViewRef){
            return mViewRef.get();
        }
        return null;
    }
}
