package com.future.bluetoothnamesystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.adapter.MyAdapter;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NamingStart extends BaseActivity {


    Spinner spChooseClass,spChooseCourse;
    private String[] mItemsCallName = new String[]{"选择班级", "选择课程", "开始点名", "查看结果"};
    private List mClassesChoosedList = new ArrayList();
    List<String> mClassesList,mCoursesList = new ArrayList<String>();
    private GridView gv;
    private List<Boolean> mSelectedList;
    private MySelectAdapter adapter;
    private ArrayAdapter mClassesChoosedAdapter,courseAdapter;
    private LinearLayout ll;
    private LinearLayout llClassChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_start);
        initData();
        initView();
    }

    public void initView() {
        ll = (LinearLayout) findViewById(R.id.linearLayout);
        llClassChoose = (LinearLayout) findViewById(R.id.ll_class_choose);


        gv = (GridView) findViewById(R.id.gv_classes_choosed);
        spChooseClass = (Spinner) findViewById(R.id.sp_choose_class);
        spChooseCourse= (Spinner) findViewById(R.id.sp_choose_course);

        spChooseClass.setAdapter(adapter);
        spChooseCourse.setAdapter(courseAdapter);
        gv.setAdapter(mClassesChoosedAdapter);


    }

    public void initData() {
        TestStudentInfoDao siDao = new TestStudentInfoDao(NamingStart.this);
        mClassesList = siDao.findClass();
        TestCourseInfoDao tcDao=new TestCourseInfoDao(NamingStart.this);
        mCoursesList=tcDao.findAllCourseNames();

        adapter = new MySelectAdapter(NamingStart.this, mClassesList);
        courseAdapter=new ArrayAdapter(NamingStart.this,android.R.layout.simple_list_item_single_choice,mCoursesList);
       // courseAdapter=new ArrayAdapter(NamingStart.this,android.R.layout.select_dialog_multichoice,mCoursesList);
        mClassesChoosedAdapter = new ArrayAdapter(NamingStart.this, R.layout.gridview_text, mClassesChoosedList);

    }

    class MySelectAdapter extends MyAdapter<String> {


        public MySelectAdapter(Context ctx, List<String> list) {
            super(ctx, list);
            mSelectedList = new ArrayList<Boolean>();
            for (int i = 0; i < mClassesList.size(); i++) {
                mSelectedList.add(false);
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(NamingStart.this, R.layout.class_item_selected, null);
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.class_select);
            final String className = mClassesList.get(position).toString();
            checkBox.setText(className);
            checkBox.setChecked(mSelectedList.get(position));


            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSelectedList.set(position, true);
                        if (!mClassesChoosedList.contains(className)) {
                            mClassesChoosedList.add(className);
                            mClassesChoosedAdapter.notifyDataSetChanged();

                        }
                    } else {
                        mSelectedList.set(position, false);
                        mClassesChoosedList.remove(className);
                        mClassesChoosedAdapter.notifyDataSetChanged();


                    }

                }
            });

            return view;
        }

//        public View getView(final int position, View convertView, ViewGroup parent) {
//           //View view = View.inflate(NamingStart.this, android.R.layout.simple_list_item_multiple_choice, null);
//            View view = View.inflate(NamingStart.this, R.layout.class_item_selecteded, null);
//            //final CheckedTextView checkBox = (CheckedTextView) view.findViewById(android.R.id.text1);
//            final CheckedTextView checkBox = (CheckedTextView) view.findViewById(R.id.ctv_checkedTextView);
//            final String className = mClassesList.get(position).toString();
//            checkBox.setText(className);
//            checkBox.setChecked(mSelectedList.get(position));
//
//
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (checkBox.isChecked()) {
//                        mSelectedList.set(position, false);
//                        if (mClassesChoosedList.contains(className)) {
//                            mClassesChoosedList.remove(className);
//                            mClassesChoosedAdapter.notifyDataSetChanged();
//                        }
//                        checkBox.setChecked(false);
//
//
//                    } else {
//
//
//                        mSelectedList.set(position, true);
//                        if (!mClassesChoosedList.contains(className)) {
//                            mClassesChoosedList.add(className);
//                            mClassesChoosedAdapter.notifyDataSetChanged();
//
//                        }
//                        checkBox.setChecked(true);
//                    }
//                }
//
//            });
//
//            return view;
//        }
    }
}
