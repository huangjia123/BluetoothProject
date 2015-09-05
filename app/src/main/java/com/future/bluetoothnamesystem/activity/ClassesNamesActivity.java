package com.future.bluetoothnamesystem.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassesNamesActivity extends BaseActivity {

    private String[] name = new String[]{"小二", "小二", "小二", "小二", "小二", "小二", "小二",
            "小二", "小二", "小二", "小二", "小二", "小二", "小二", "小二", "小二"};

    private String[] id = new String[]{"20121554124", "20121554124", "20121554124", "20121554124",
            "20121554124", "20121554124", "20121554124", "20121554124", "20121554124",
            "20121554124", "20121554124", "20121554124", "20121554124", "20121554124",
            "20121554124", "20121554124"};

    private String[] address = new String[]{"18.96.126.1", "18.96.126.1", "18.96.126.1",
            "18.96.126.1", "18.96.126.1", "18.96.126.1", "18.96.126.1", "18.96.126.1",
            "18.96.126.1", "18.96.126.1", "18.96.126.1", "18.96.126.1", "18.96.126.1",
            "18.96.126.1", "18.96.126.1", "18.96.126.1"};
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_names);

        list = new ArrayList();
           for (int i = 0; i <name.length ; i++) {
            Map lists = new HashMap();
            lists.put("names", name[i]);
            lists.put("id", id[i]);
            lists.put("address", address[i]);
               list.add(lists);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.students_names_list_view,
                new String[]{"names", "id", "address"}, new int[]{R.id.student_names,
                R.id.student_id, R.id.student_MacAddress});
        ListView listview = (ListView) findViewById(R.id.lv_studentNames);
        listview.setAdapter(adapter);

    }
}
