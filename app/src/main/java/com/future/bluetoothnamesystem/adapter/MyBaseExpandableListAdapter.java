package com.future.bluetoothnamesystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.NamingResult;

import java.util.List;
import java.util.Map;

/**
 * Created by baiju on 2015/8/29.
 */
public class MyBaseExpandableListAdapter<T> extends BaseExpandableListAdapter {
    private List<Map<String, T>> group;
    private List<List<Map<String, T>>> child;
    private Context context;
    private Map<String, Object> childItemMap;

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

    public View setRadioButton(View convertView, final Map<String, Object> childItemMap) {
        final RadioButton rbBreaks = (RadioButton) convertView.findViewById(R.id.breaks);
        final RadioButton rbLate = (RadioButton) convertView.findViewById(R.id.late);
        final RadioButton rbNon_arrival = (RadioButton) convertView.findViewById(R.id.non_arrival);
        System.out.println("==============childeMap.size()============"+childItemMap.size());

        if ((Boolean) childItemMap.get("non_arrival")) {
            rbBreaks.setChecked(false);
            rbLate.setChecked(false);
            rbNon_arrival.setChecked(true);
        }
        if ((Boolean) childItemMap.get("break")) {
            rbBreaks.setChecked(true);
            rbLate.setChecked(false);
            rbNon_arrival.setChecked(false);
        }
        if ((Boolean) childItemMap.get("late")) {
            rbBreaks.setChecked(false);
            rbLate.setChecked(false);
            rbNon_arrival.setChecked(true);
        }

        rbBreaks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbBreaks.isChecked()){
                    childItemMap.put("break",true);
                    rbLate.setChecked(false);
                    rbNon_arrival.setChecked(false);
                    System.out.println("breaks  checked true");
                }else{
                    childItemMap.put("break",false);
                    System.out.println("breaks  checked false");
                }

            }
        });

        rbNon_arrival.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbLate.isChecked()){
                    childItemMap.put("late",true);
                    rbBreaks.setChecked(false);
                    rbNon_arrival.setChecked(false);
                    System.out.println("rbLate  checked true");
                }else{
                    childItemMap.put("late",false);
                    System.out.println("rbLate  checked false");
                }

            }
        });
        rbNon_arrival.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbNon_arrival.isChecked()){
                    childItemMap.put("non_arrival",true);
                    rbLate.setChecked(false);
                    rbBreaks.setChecked(false);
                    System.out.println("rbNon_arrival  checked true");
                }else{
                    childItemMap.put("non_arrival",false);
                    System.out.println("rbNon_arrival  checked false");
                }

            }
        });
        return convertView;
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
        tvNumber.setText(mGroupName);
        tvNumber.setText(mStuCount);
        return convertView;
    }

    int groupPosition,childPosition;
    @Override
    public View getChildView( int groupPositions,  int childPositions, boolean isLastChild, View convertView, ViewGroup parent) {
        groupPosition=groupPositions;
        childPosition=childPositions;
        childItemMap = (Map<String, Object>) getChild(groupPosition, childPosition);

        if(convertView==null){
            convertView=View.inflate(context,R.layout.item_result_nocoming_result,null);
        }


        final RadioButton rbBreaks = (RadioButton) convertView.findViewById(R.id.breaks);
        final RadioButton rbLate = (RadioButton) convertView.findViewById(R.id.late);
        final RadioButton rbNon_arrival = (RadioButton) convertView.findViewById(R.id.non_arrival);
        System.out.println("==============childeMap.size()============" + childItemMap.size());

        if ((Boolean) childItemMap.get("non_arrival")) {
            rbBreaks.setChecked(false);
            rbLate.setChecked(false);
            rbNon_arrival.setChecked(true);
        }
        if ((Boolean) childItemMap.get("break")) {
            rbBreaks.setChecked(true);
            rbLate.setChecked(false);
            rbNon_arrival.setChecked(false);
        }
        if ((Boolean) childItemMap.get("late")) {
            rbBreaks.setChecked(false);
            rbLate.setChecked(false);
            rbNon_arrival.setChecked(true);
        }

        rbBreaks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbBreaks.isChecked()){
                    ((Map<String, Object>) child.get(groupPosition).get(childPosition)).put("break", true);
                    rbLate.setChecked(false);
                    rbNon_arrival.setChecked(false);
                    System.out.println("breaks  checked true");
                }else{
                    ((Map<String, Object>) child.get(groupPosition).get(childPosition)).put("break", false);
                    System.out.println("breaks  checked false");
                }

            }
        });

        rbNon_arrival.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbLate.isChecked()){
                    ((Map<String, Object>) child.get(groupPosition).get(childPosition)).put("late", true);
                    rbBreaks.setChecked(false);
                    rbNon_arrival.setChecked(false);
                    System.out.println("rbLate  checked true");
                }else{
                    ((Map<String, Object>) child.get(groupPosition).get(childPosition)).put("late", false);
                    System.out.println("rbLate  checked false");
                }

            }
        });
        rbNon_arrival.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rbNon_arrival.isChecked()){
                    ((Map<String, Object>) child.get(groupPosition).get(childPosition)).put("non_arrival", true);
                    rbLate.setChecked(false);
                    rbBreaks.setChecked(false);
                    System.out.println("rbNon_arrival  checked true");
                }else{
                    ((Map<String, Object>) child.get(groupPosition).get(childPosition)).put("non_arrival", false);
                    System.out.println("rbNon_arrival  checked false");
                }

            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
