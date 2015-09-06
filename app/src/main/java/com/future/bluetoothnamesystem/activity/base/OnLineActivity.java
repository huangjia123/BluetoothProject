package com.future.bluetoothnamesystem.activity.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.view.LineGridView;

public class OnLineActivity extends BaseActivity {
    private LineGridView gvSet1;

    private String[] mItems1 = new String[] { "在线点名", "查看结果", "查看课程", "上传数据"};

    private int[] mPics1 = new int[] { R.mipmap.horn,R.mipmap.good,
            R.mipmap.write,R.mipmap.ture};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_name);
        gvSet1=(LineGridView)findViewById(R.id.gv_setting_call);
        gvSet1.setAdapter(new HomeAdapter());
    }
    class HomeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems1.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems1[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(OnLineActivity.this,
                    R.layout.activity_setting2, null);
            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);

            tvItem.setText(mItems1[position]);
            ivItem.setImageResource(mPics1[position]);
            return view;
        }

    }




}
