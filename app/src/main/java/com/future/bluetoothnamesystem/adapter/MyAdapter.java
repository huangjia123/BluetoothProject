package com.future.bluetoothnamesystem.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by baiju on 2015/8/24.
 */
public abstract class MyAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    public MyAdapter(Context ctx, List<T> list) {
        this.list=list;
        context=ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
