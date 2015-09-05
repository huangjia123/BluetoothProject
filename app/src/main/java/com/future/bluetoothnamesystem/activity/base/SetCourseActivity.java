package com.future.bluetoothnamesystem.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.view.LineGridView;

public class SetCourseActivity extends AppCompatActivity {
    private GridView lvaddCourse;

    private String[] mItems = new String[] { "大学物理", "高级数学", "大学英语", "物联网导论",
            "疯狂android", "C语言" ,"计算机组成与接口技术","嵌入式原理技术"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_setting);
        lvaddCourse=(GridView)findViewById(R.id.lv_add_course);
        lvaddCourse.setAdapter(new HomeAdapter());

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
            View view = View.inflate(SetCourseActivity.this,
                    R.layout.activity_set_course, null);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            tvItem.setText(mItems[position]);
            return view;
        }

    }

}
