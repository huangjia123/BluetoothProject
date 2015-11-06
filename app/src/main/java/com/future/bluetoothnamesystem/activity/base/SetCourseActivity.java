package com.future.bluetoothnamesystem.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.bean.CourseInfo;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;
import com.future.bluetoothnamesystem.dialog.AddCourseDialog;
import com.future.bluetoothnamesystem.dialog.DeleteCourseDialog;
import com.future.bluetoothnamesystem.dialog.UpdateCourseDialog;
import com.future.bluetoothnamesystem.view.LineGridView;

import java.util.List;

public class SetCourseActivity extends BaseActivity {
    private GridView lvaddCourse;
    private TestCourseInfoDao testCourseInfoDao;
    private ImageView addCourseImg ,deleteCourseImg,updateCourseImg;

    private String[] mItems;// = new String[] { "大学物理", "高级数学", "大学英语", "物联网导论",
    // "疯狂android", "C语言" ,"计算机组成与接口技术","嵌入式原理技术"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_setting);
        addCourseImg = (ImageView) findViewById(R.id.iv_add1);
        deleteCourseImg = (ImageView) findViewById(R.id.iv_delete);
        updateCourseImg = (ImageView) findViewById(R.id.iv_item);
        lvaddCourse=(GridView)findViewById(R.id.lv_add_course);
        testCourseInfoDao = new TestCourseInfoDao(SetCourseActivity.this);
        List<CourseInfo> courses = testCourseInfoDao.findAllCourseName();
        mItems = new String[courses.size()];
        for (int i = 0;i < courses.size();i++){
            mItems[i] = courses.get(i).getCourseName();
        }
        lvaddCourse.setAdapter(new HomeAdapter());
        addListening();

    }

    public void addListening(){
        addCourseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetCourseActivity.this, "增加课程", Toast.LENGTH_SHORT).show();
                AddCourseDialog addCourseDialog = new AddCourseDialog(SetCourseActivity.this);
                addCourseDialog.show();
            }
        });
        updateCourseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetCourseActivity.this,"修改课程",Toast.LENGTH_SHORT).show();
                UpdateCourseDialog updateCourseDialog=new UpdateCourseDialog(SetCourseActivity.this);
                updateCourseDialog.show();
            }
        });
        deleteCourseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetCourseActivity.this, "删除课程", Toast.LENGTH_SHORT).show();
                DeleteCourseDialog deleteCourseDialog=new DeleteCourseDialog(SetCourseActivity.this);
                deleteCourseDialog.show();
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
            View view = View.inflate(SetCourseActivity.this,
                    R.layout.activity_set_course, null);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            tvItem.setText(mItems[position]);
            return view;
        }
    }
}
