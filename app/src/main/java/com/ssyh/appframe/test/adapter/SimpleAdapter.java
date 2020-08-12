package com.ssyh.appframe.test.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssyh.appframe.R;

import java.util.List;

public class SimpleAdapter extends BaseAdapter<String>{
    private int createViewCount;
    public SimpleAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        createViewCount ++;
        Log.d("SimpleAdapter", "onCreateViewHolder:   createViewCount: "+createViewCount);
        return new MyViewHoder(createItemView( R.layout.list_item_simple,viewGroup));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Log.d("SimpleAdapter", "onBindViewHolder:   createViewCount: "+createViewCount);
        MyViewHoder myViewHoder = (MyViewHoder) viewHolder;
        myViewHoder.tv_text.setText(mDatas.get(position));
    }

    static class MyViewHoder extends BaseViewHolder {

        private TextView tv_text;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_text = findViewById(R.id.tv_text);
        }
    }
}
