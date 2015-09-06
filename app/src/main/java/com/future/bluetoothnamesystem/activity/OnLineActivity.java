package com.future.bluetoothnamesystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;

public class OnLineActivity extends AppCompatActivity {

    private GridView gvSet1;
    private String[] mItemsCallName = new String[] { "在线点名", "查看结果", "查看课程", "查看数据"};

    private int[] mPicsCallName = new int[] { R.mipmap.horn,R.mipmap.good,
            R.mipmap.write,R.mipmap.v};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_name);

        gvSet1= (GridView) findViewById(R.id.gv_setting_call);
        HomeAdapter adapter = new HomeAdapter(mItemsCallName);
        gvSet1.setAdapter(adapter);
    }

    class HomeAdapter extends BaseAdapter {

        private String[] mItemCallNames;
        public HomeAdapter(String[] list) {
            this.mItemCallNames=list;
        }

        @Override
        public int getCount() {
            return mItemCallNames.length;
        }

        @Override
        public Object getItem(int position) {
            return mItemCallNames[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(OnLineActivity.this,
                    R.layout.activity_setting2, null);
            ImageView ivItem = (ImageView) convertView.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) convertView.findViewById(R.id.tv_item);

            tvItem.setText(mItemCallNames[position]);
            ivItem.setImageResource(mPicsCallName[position]);
            return convertView;
        }

    }
}



