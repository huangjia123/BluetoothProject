package com.future.bluetoothnamesystem.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.adapter.MyAdapter;
import com.future.bluetoothnamesystem.bean.NamingRecard;
import com.future.bluetoothnamesystem.db.dao.BluetoothDao;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;

import java.util.ArrayList;
import java.util.List;

public class CheckResultActivity extends BaseActivity {

    List<String> mClassesList, mCoursesList = new ArrayList<String>();
    private MyNamingRecordAdapter myNamingRecordAdapter;
    private List<NamingRecard> namingRecordList;
    private BluetoothDao dao;
    private Spinner spChooseCourse;
    private Spinner spChooseClass;
    private ArrayAdapter courseAdapter;
    private ArrayAdapter classAdapter;
    private ListView listview;
    private TestStudentInfoDao siDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);
        siDao = new TestStudentInfoDao(CheckResultActivity.this);
        initData();
        initView();


    }

    /**
     * 初始化页面组件
     */
    public void initView() {
        listview = (ListView) findViewById(R.id.lv_result);
        spChooseCourse = (Spinner) findViewById(R.id.sp_choose_course);
        spChooseClass = (Spinner) findViewById(R.id.sp_choose_class);

        spChooseCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //监听课程spinner的选项变化，根据spChooseCourse选择的课程名，筛选出上这个课并点过名的班级
                mClassesList = siDao.findClassByCoursename(spChooseCourse.getSelectedItem().toString());
                mClassesList.add(0, "选择班级");
                classAdapter = new ArrayAdapter(CheckResultActivity.this, R.layout.simple_list_item, mClassesList);
                spChooseClass.setAdapter(classAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listview.setAdapter(myNamingRecordAdapter);
        spChooseCourse.setAdapter(courseAdapter);

        mClassesList = new ArrayList<String>();
        mClassesList.add(0, "选择班级");
        classAdapter = new ArrayAdapter(CheckResultActivity.this, R.layout.simple_list_item, mClassesList);
        spChooseClass.setAdapter(classAdapter);

        Button bnSearch = (Button) findViewById(R.id.search_button);
        bnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectClass = spChooseClass.getSelectedItem().toString();
                String selectCourse = spChooseCourse.getSelectedItem().toString();
                namingRecordList = dao.findByCourseAndClass(selectClass, selectCourse);
                myNamingRecordAdapter = new MyNamingRecordAdapter(CheckResultActivity.this, namingRecordList);
                listview.setAdapter(myNamingRecordAdapter);
            }
        });

    }

    /**
     * 初始化页面加载数据
     */
    public void initData() {
        dao = new BluetoothDao(CheckResultActivity.this);
        namingRecordList = dao.findAll();

//        ArrayList list=new ArrayList();
//        list.add("计科121");
//        list.add("计科122");
//        int size=list.size();
//        String[] mlist= (String[]) list.toArray(new String[size]);
//        namingRecordList = dao.findByClases2(list);
//        Toast.makeText(CheckResultActivity.this, namingRecordList.size(), Toast.LENGTH_SHORT).show();

        TestCourseInfoDao tcDao = new TestCourseInfoDao(CheckResultActivity.this);
        mCoursesList = tcDao.findAllCourseNames();
        mCoursesList.add(0, "选择课程");

        myNamingRecordAdapter = new MyNamingRecordAdapter(CheckResultActivity.this, namingRecordList);
        courseAdapter = new ArrayAdapter(CheckResultActivity.this, R.layout.simple_list_item, mCoursesList);
    }

    static class ViewHolder {
        TextView stuId;
        TextView stuName;
        TextView tvArrivalTimes;
        TextView tvNonArrivalTimes;
        TextView tvBreaksTimes;
        TextView tvLateTimes;

    }

    /**
     * 自定义adapter，用来适配namingRecord表中的数据到 点名记录
     */
    class MyNamingRecordAdapter extends MyAdapter<NamingRecard> {
        private NamingRecard namingRecards;
        private List<NamingRecard> mList;
        private Context context;


        public MyNamingRecordAdapter(Context ctx, List<NamingRecard> list) {
            super(ctx, list);
            this.context = ctx;
            this.mList = list;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.check_result_list_view, null);
                holder = new ViewHolder();

                holder.stuId = (TextView) convertView.findViewById(R.id.student_id);
                holder.stuName = (TextView) convertView.findViewById(R.id.student_names);
                holder.tvArrivalTimes = (TextView) convertView.findViewById(R.id.tv_arrival_times);
                holder.tvNonArrivalTimes = (TextView) convertView.findViewById(R.id.tv_non_arrival_times);
                holder.tvLateTimes = (TextView) convertView.findViewById(R.id.late_times);
                holder.tvBreaksTimes = (TextView) convertView.findViewById(R.id.breaks_times);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            namingRecards = mList.get(position);
            holder.stuId.setText(namingRecards.getStu_id() + "");
            holder.stuName.setText(namingRecards.getName());

            if (namingRecards.getBreaks() == null) {
                holder.tvBreaksTimes.setText(namingRecards.getBreaks());
            } else {
                holder.tvBreaksTimes.setText("2");
            }
            holder.tvNonArrivalTimes.setText(namingRecards.getNon_arrival());
            holder.tvArrivalTimes.setText(namingRecards.getArrival());
            holder.tvLateTimes.setText(namingRecards.getLate());
            return convertView;
        }
    }
}
