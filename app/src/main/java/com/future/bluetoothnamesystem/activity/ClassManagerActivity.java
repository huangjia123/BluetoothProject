package com.future.bluetoothnamesystem.activity;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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
        Toast.makeText(ClassManagerActivity.this,classNames.isEmpty()+"",Toast.LENGTH_SHORT).show();
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
                Intent it = new Intent(ClassManagerActivity.this, ClassesNamesActivity.class);
                it.putExtra("className", className);
                startActivity(it);
            }
        });
//王少峰*********************************************************
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                AlertDialog alert =
                        new AlertDialog.Builder(ClassManagerActivity.this).create();
                alert.setIcon(R.drawable.notification_template_icon_bg);
                alert.setTitle("删除班级:");
                alert.setMessage("是否删除？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ClassManagerActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(ClassManagerActivity.this,"确定",Toast .LENGTH_SHORT).show();

                    }
                });
                alert.show();
                return true;
            }
        });
        //*********************************************************

    }
    public void add(View view) {
        Toast.makeText(ClassManagerActivity.this, "可以用", Toast.LENGTH_SHORT).show();
    }
    public void delete(View view){

        ArrayList<String> cn =(ArrayList<String>)classNames;
        String [] names=new String[cn.size()];
        for (int i=0;i<cn.size();i++){
            names[i]=cn.get(i);
        }
        Toast.makeText(ClassManagerActivity.this,names[0]+"",Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder=new AlertDialog.Builder(ClassManagerActivity.this);
        builder.setTitle("请选择您想要删除的班级：");
        builder.setSingleChoiceItems(names, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
//王少峰*********************************************************
}
