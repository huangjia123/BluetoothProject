package com.future.bluetoothnamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.activity.base.SetCourseActivity;
import com.future.bluetoothnamesystem.view.LineGridView;

public class SettingActivity extends BaseActivity {
    private LineGridView  gvSet;
    private String[] mItems = new String[] { "课程设置", "班级管理", "导出数据", "恢复数据",
            "教师信息", "设置密码" };
    private int[] mPics = new int[] { R.mipmap.set,R.mipmap.manage,
            R.mipmap.ban,R.mipmap.car,R.mipmap.sigh,R.mipmap.lock};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        gvSet = (LineGridView) findViewById(R.id.gv_setting);
        gvSet.setAdapter(new HomeAdapter());
        gvSet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        //
                        startActivity(new Intent(SettingActivity.this, SetCourseActivity.class));
                        break;

                    case 1:
                        //
                        startActivity(new Intent(SettingActivity.this, ClassManagerActivity.class));
                        break;
                    case 2:
                        //
                        startActivity(new Intent(SettingActivity.this, ExportData.class));
                        break;
                    case 3:
                        //
                        //startActivity(new Intent(SettingActivity.this, SetCourseActivity.class));
                        Toast.makeText(SettingActivity.this,
                                "正在从远程数据库中，回复数据，请稍候。。。这个功能--陈书梦",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        //设置教师使用者的信息
                        Toast.makeText(SettingActivity.this,
                                "设置教师信息，这个功能弹窗实现",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        //
                        startActivity(new Intent(SettingActivity.this,PasswordLock.class));
                        break;
                    default:
                        break;
                }
            }
        });

    }

    class HomeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(SettingActivity.this,
                    R.layout.activity_setting2, null);
            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);

            tvItem.setText(mItems[position]);
            ivItem.setImageResource(mPics[position]);
            return view;
        }

    }
}



