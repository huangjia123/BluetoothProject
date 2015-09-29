package com.future.bluetoothnamesystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.adapter.MyAdapter;
import com.future.bluetoothnamesystem.bean.CourseInfo;

import java.util.ArrayList;
import java.util.List;

public class ExportData extends BaseActivity {

    public List<CourseInfo> courseInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_data);
        initData();
    }

    public void initData() {
        Spinner classSP = (Spinner) findViewById(R.id.sp_choose_class);

        courseInfoList=new ArrayList<CourseInfo>() ;
        /*courseInfoList.add(new CourseInfo(false, "计科121"));
        courseInfoList.add(new CourseInfo(false, "计科122"));
        courseInfoList.add(new CourseInfo(false, "计科123"));
        courseInfoList.add(new CourseInfo(false, "计科125"));*/
        InnerAdapter innerAdapter = new InnerAdapter(ExportData.this,courseInfoList);
        classSP.setAdapter(innerAdapter);

        classSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*if (courseInfoList.get(position).isChecked()) {
                    courseInfoList.get(position).setIsChecked(false);
                } else {
                    courseInfoList.get(position).setIsChecked(true);

                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    class InnerAdapter extends MyAdapter<CourseInfo> {
        /**
         * 课程实体
         */
        private CourseInfo courseInfo;
        private List<CourseInfo> lists;

        public InnerAdapter(Context ctx, List<CourseInfo> list) {
            super(ctx, list);
            this.lists = list;
            System.out.println(lists.size());
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;

            if (convertView == null) {
                convertView = View.inflate(ExportData.this, R.layout.item_course_selected, null);
                holder = new ViewHolder();
                holder.cbClass = (CheckBox) convertView.findViewById(R.id.checkBox);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if(holder.cbClass==null){
                System.out.println("================空=================");
            }
            /*holder.cbClass.setChecked(lists.get(position).isChecked());

            holder.cbClass.setText(lists.get(position).getItemName());*/
            System.out.println("================" + lists.get(position).toString());

            return convertView;
        }
    }

    static class ViewHolder {
        CheckBox cbClass;
    }
}
