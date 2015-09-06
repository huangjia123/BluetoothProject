package com.future.bluetoothnamesystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class NamingStart extends BaseActivity {

    private GridView gvSet1;
    private String[] mItemsCallName = new String[]{"在线点名", "查看结果", "查看课程", "查看数据"};
    private List<String> list = new ArrayList<String>();

    private int[] mPicsCallName = new int[]{R.mipmap.horn, R.mipmap.good,
            R.mipmap.write, R.mipmap.v};
    private LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_start);
        lin = (LinearLayout) findViewById(R.id.linearLayout);
        for (int i = 0; i < 4; i++) {
            list.add(mItemsCallName[i]);
        }
        gvSet1 = (GridView) findViewById(R.id.gv_setting_call);
        InnerAdapter adapter = new InnerAdapter(NamingStart.this, list);
        gvSet1.setAdapter(adapter);

    }

    class InnerAdapter extends MyAdapter<String> {

        public InnerAdapter(Context ctx, List<String> list) {
            super(ctx, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater mInflater = LayoutInflater.from(NamingStart.this);
            if (convertView == null) {
                convertView = mInflater.inflate(
                        R.layout.item_select, parent, false);
                holder = new ViewHolder();
                holder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
                holder.ivItem = (ImageView) convertView.findViewById(R.id.iv_item);

                ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();

                layoutParams.height = lin.getHeight() / 2;
                convertView.setLayoutParams(layoutParams);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (convertView.getHeight() == 0) {
                ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();

                layoutParams.height = lin.getHeight() / 2;
                convertView.setLayoutParams(layoutParams);
            }

            holder.tvItem.setText(list.get(position).toString());
            holder.ivItem.setImageResource(mPicsCallName[position]);
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tvItem;
        ImageView ivItem;
    }
}
