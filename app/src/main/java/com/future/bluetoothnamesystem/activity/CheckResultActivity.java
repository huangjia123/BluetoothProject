package com.future.bluetoothnamesystem.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckResultActivity extends BaseActivity {

    private String[] name = new String[]{"小二", "小二", "小二", "小二", "小二", "小二",
            "小二", "小二", "小二", "小二", "小二", "小二"};
    private String[] id = new String[]{"20112554124", "20112554124", "20112554124", "20112554124",
            "20112554124", "20112554124", "20112554124", "20112554124", "20112554124", "20112554124",
            "20112554124", "20112554124"};
    private int[] times1 = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
    private int[] times2 = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
    private int[] times3 = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
    private int[] times4 = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);

        List list = new ArrayList();
        for (int i = 0; i < name.length; i++) {
            Map lists = new HashMap();
            lists.put("name", name[i]);
            lists.put("id", id[i]);
            lists.put("times1", times1[i]);
            lists.put("times2", times2[i]);
            lists.put("times3", times3[i]);
            lists.put("times4", times4[i]);
            list.add(lists);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.check_result_list_view,
                new String[]{"name", "id", "times1", "times2", "times3", "times4"},
                new int[]{R.id.student_names, R.id.student_id, R.id.tv_arrival_times,
                R.id.tv_non_arrival_times, R.id.breaks_times, R.id.late_times});
        ListView listview = (ListView) findViewById(R.id.lv_result);
        listview.setAdapter(adapter);

    }
}
