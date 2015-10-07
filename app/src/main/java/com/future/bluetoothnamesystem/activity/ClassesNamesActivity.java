package com.future.bluetoothnamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView className;
    private List list;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_names);
        className = (TextView) findViewById(R.id.classname);
        intent = getIntent();
        className.setText(intent.getStringExtra("className"));
        Toast.makeText(ClassesNamesActivity.this, intent.getStringExtra("className"), Toast.LENGTH_SHORT).show();
        list = new ArrayList();
        for (int i = 0; i < name.length; i++) {
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

    public void add(View view) {
        Toast.makeText(ClassesNamesActivity.this, "add", Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        Toast.makeText(ClassesNamesActivity.this, "update", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        Toast.makeText(ClassesNamesActivity.this, "delete", Toast.LENGTH_SHORT).show();
    }

}
