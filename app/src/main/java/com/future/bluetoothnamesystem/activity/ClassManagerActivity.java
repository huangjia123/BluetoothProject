package com.future.bluetoothnamesystem.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.bean.ClassInfo;
import com.future.bluetoothnamesystem.db.dao.DataBaseHelper;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassManagerActivity extends BaseActivity {
    DataBaseHelper helper;
    private String[] name = new String[]{"计科1", "计科1", "计科1", "计科1", "计科1",
            "计科1", "计科1", "计科1", "计科1"};
    private List<String> classNames;
    private List<String> stuNumbers;
    private ListView list;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);
        iniData();

        initView();
    }

    public void iniData() {
        TestStudentInfoDao tsiDao = new TestStudentInfoDao(ClassManagerActivity.this);
        classNames = tsiDao.findClass();
        adapter = new ArrayAdapter<String>(this, R.layout.classes_names_list_view,
                R.id.etvnames_item, classNames);


    }

    public void initView(){
        list = (ListView) findViewById(R.id.lv_classNames);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String className = classNames.get(position);
                Intent it=new Intent(ClassManagerActivity.this,ClassesNamesActivity.class);
                it.putExtra("className",className);
                startActivity(it);
            }
        });
    }
    public void add(View view) {
        Toast.makeText(ClassManagerActivity.this, "可以用", Toast.LENGTH_SHORT).show();
    }
}
