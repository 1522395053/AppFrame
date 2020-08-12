package com.ssyh.appframe.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<T> mDatas;


    public BaseAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @NonNull
    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position);

    @Override
    public abstract void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position);

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    protected View createItemView(int viewResourceId, ViewGroup parent){
        return LayoutInflater.from(mContext).inflate(viewResourceId, parent , false);
    }




    static class BaseViewHolder extends RecyclerView.ViewHolder{


        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected <T extends View>  T findViewById(int resId){
            return itemView.findViewById(resId);
        }
    }
}
