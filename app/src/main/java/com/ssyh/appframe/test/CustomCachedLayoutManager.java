package com.ssyh.appframe.test;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CustomCachedLayoutManager extends RecyclerView.LayoutManager {
    private int mTotalItemHeight;
    private Context mContext;

    public CustomCachedLayoutManager(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (state.getItemCount() == 0){
            removeAndRecycleAllViews(recycler);
            return;
        }

        //初始化时
        int offsetY = 0;

        for (int i = 0; i < getItemCount(); i++) {
            View viewForPosition = recycler.getViewForPosition(i);
            addView(viewForPosition);
            measureChildWithMargins(viewForPosition,0,0);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);


            layoutDecorated(viewForPosition,0,offsetY,decoratedMeasuredWidth,offsetY + decoratedMeasuredHeight);

            offsetY += decoratedMeasuredHeight;
        }

        mTotalItemHeight = Math.max(offsetY,getVerticalSpace());
//        mTotalItemHeight = offsetY;
    }


    @Override
    public boolean canScrollVertically() {
//        return super.canScrollVertically();
        return true;
    }

    private int sumDy = 0;//实际滑动的距离总和

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        return super.scrollVerticallyBy(dy, recycler, state);
        int offsetY = dy;
        //sumDy = 0 时说明 归位了，即到顶了，即恢复初始位置了 即顶部位置
        if (sumDy + dy < 0){
            offsetY = 0 - sumDy;//再滑动 0-sumdy 就恰好到顶了
        }

        else if (sumDy + dy > mTotalItemHeight - getVerticalSpace()){
            offsetY = mTotalItemHeight - getVerticalSpace() - sumDy;
        }



        sumDy += offsetY;//加上实际滑动的距离

        Log.d("scrollVerticallyBy", "scrollVerticallyBy:   dy:"+dy +"    sumDy: "+ sumDy );



        offsetChildrenVertical(-offsetY);
        return dy;
    }


    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }
}
