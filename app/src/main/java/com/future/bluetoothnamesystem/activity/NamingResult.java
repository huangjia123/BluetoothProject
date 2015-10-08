package com.future.bluetoothnamesystem.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.bean.StudentInfo;
import com.future.bluetoothnamesystem.db.dao.BluetoothDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NamingResult extends BaseActivity {

    private List<StudentInfo> studentInfos;
    private ExpandableListView resultView;
    private List<String> listTag = new ArrayList<String>();
    private SimpleExpandableListAdapter mComingAdapter;
    public MyBaseExpandableListAdapter mNoComingAdapter;
    public String courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_result);
        resultView = (ExpandableListView) findViewById(R.id.result_naming_listview);
        initData();
        resultView.setAdapter(mNoComingAdapter);
    }

    //用于显示点名结果的列表
    public void initData() {
       // courseName = "英语";
        BluetoothDao dao = new BluetoothDao(this);
        List<List<Map<String, String>>> mList = new ArrayList<List<Map<String, String>>>();
        List<List<Map<String, Object>>> mNoComingList = new ArrayList<List<Map<String, Object>>>();
        //选中的班级
        /**************************************************************/
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        ArrayList<String> selectClassName=bundle.getStringArrayList("classname");
        courseName=bundle.getString("course");
        /**************************************************************/
        List<Map<String, String>> mClassGroup = dao.findClass(selectClassName);
        for (Map<String, String> map : mClassGroup) {
            String groupName = map.get("group");
            List<Map<String, String>> mStuItem = dao.findItem(groupName, courseName);
            List<Map<String, Object>> mStuNoComingItem = dao.findNoComingResult(groupName, courseName);
            mList.add(mStuItem);
            mNoComingList.add(mStuNoComingItem);
        }
        mComingAdapter = new SimpleExpandableListAdapter(this, mClassGroup,
                R.layout.tag_item_result_naming, new String[]{"group", "stuCount"},
                new int[]{R.id.class_tag, R.id.total_number}, mList, R.layout.item_result_naming,
                new String[]{"stu_id", "stu_name"}, new int[]{R.id.stu_id, R.id.id_name});

        mNoComingAdapter = new MyBaseExpandableListAdapter(this, mClassGroup,
                "group", mNoComingList);

    }

    class MyBaseExpandableListAdapter<T> extends BaseExpandableListAdapter {
        private List<Map<String, T>> group;
        private List<List<Map<String, T>>> child;
        private Context context;
        private Map<String, Object> childItemMap;
        private Map<Integer, Map<Integer, Integer>> mTempSelectMap = new HashMap<Integer, Map<Integer, Integer>>();
        private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        public MyBaseExpandableListAdapter() {
        }
        public MyBaseExpandableListAdapter(Context context, List<Map<String, T>> group, String groupName, List<List<Map<String, T>>> child) {
            this.context = context;
            this.group = group;
            this.child = child;
        }


        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child.get(groupPosition).size();
        }

        /**
         * 有错呢
         *
         * @param groupPosition
         * @return
         */
        @Override
        public Object getGroup(int groupPosition) {
            return group.get(groupPosition);
        }

        /**
         * @param groupPosition
         * @param childPosition
         * @return 一个Map集合
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child.get(groupPosition).get(childPosition);
        }
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.tag_item_result_naming, null);
            }
            TextView tv_tag = (TextView) convertView.findViewById(R.id.class_tag);
            TextView tvNumber = (TextView) convertView.findViewById(R.id.total_number);
            String mGroupName = (String) this.group.get(groupPosition).get("group");
            String mStuCount = (String) this.group.get(groupPosition).get("stuCount");
            tv_tag.setText(mGroupName);
            tvNumber.setText(mStuCount);
            return convertView;
        }
        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder holder;

            childItemMap = (Map<String, Object>) getChild(groupPosition, childPosition);
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_result_nocoming_result, null);
                holder = new ViewHolder();
                holder.stuId = (TextView) convertView.findViewById(R.id.stu_id);
                holder.stuName = (TextView) convertView.findViewById(R.id.id_name);
                holder.rbBreaks = (RadioButton) convertView.findViewById(R.id.breaks);
                holder.rbNon_arrival = (RadioButton) convertView.findViewById(R.id.non_arrival);
                holder.rbarrival = (RadioButton) convertView.findViewById(R.id.arrival);
                holder.rbLate = (RadioButton) convertView.findViewById(R.id.late);
                holder.rgThisTime = (RadioGroup) convertView.findViewById(R.id.this_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.groupPosition=groupPosition;
            holder.childPosition=childPosition;
            holder.rgThisTime.setTag(holder.stuId);
            holder.rgThisTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    map.put(childPosition, checkedId);
                    System.out.println("我把第" + childPosition + "个Map的值设为了CheckID=" + checkedId +
                            "map的大小为" + map.size());
                    Set<Map.Entry<Integer, Integer>> test = map.entrySet();
                    for (Map.Entry<Integer, Integer> m : test) {
                       // System.out.println("key===================" + m.getKey() + "value===================" + m.getValue());
                    }
                    mTempSelectMap.put(groupPosition, map);
                    System.out.println("mTempSelectMap的大小为" + mTempSelectMap.size()+"groupPosition=="+groupPosition);

                }
            });
            holder.stuId.setText(childItemMap.get("stu_id") + "");
            holder.stuName.setText(childItemMap.get("stu_name") + "");
            Integer radioCheck = null;
            Map<Integer, Integer> maps = mTempSelectMap.get(holder.groupPosition);
            if (maps != null) {
                radioCheck = map.get(holder.childPosition);
            }

            if (radioCheck != null) {

                RadioButton radioButton = (RadioButton) convertView.findViewById(radioCheck);
                radioButton.setChecked(true);
            } else {
                switch (Integer.parseInt(childItemMap.get("this_time").toString())) {
                    case 0:
                        holder.rbNon_arrival.setChecked(true);

                        break;
                    case 1:

                        holder.rbarrival.setChecked(true);
                        break;
                    case 2:
                        holder.rbLate.setChecked(true);

                        break;
                    case 3:
                        holder.rbBreaks.setChecked(true);

                        break;
                }
            }

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

    static class ViewHolder {
        int groupPosition;
        int childPosition;
        TextView stuId;
        TextView stuName;
        RadioGroup rgThisTime;
        RadioButton rbBreaks;
        RadioButton rbLate;
        RadioButton rbNon_arrival;
        RadioButton rbarrival;
    }
}