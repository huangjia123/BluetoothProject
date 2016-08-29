package com.future.bluetoothnamesystem.activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.db.dao.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ClassesNamesActivity extends BaseActivity {
    private TextView className;
    private Intent intent;
    SimpleCursorAdapter adapter;
    ListView listview;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_names);
        className = (TextView) findViewById(R.id.classname);
        listview= (ListView) findViewById(R.id.lv_studentNames);
        intent = getIntent();
        className.setText(intent.getStringExtra("className"));
        dataBaseHelper=new DataBaseHelper(this);
        Toast.makeText(ClassesNamesActivity.this, intent.getStringExtra("className"), Toast.LENGTH_SHORT).show();
        db=dataBaseHelper.getReadableDatabase();
        String sql="select stu_id AS _id,stu_name,macAddress from student_information where class_name='"+intent.getStringExtra("className")+"'";
        cursor=db.rawQuery(sql,null);
        listViewBuju(cursor);
    }
    public void listViewBuju(Cursor cursor) {
        adapter= new SimpleCursorAdapter(ClassesNamesActivity.this,
                R.layout.students_names_list_view, cursor, new String[]{"_id","stu_name","macAddress"},
                new int[]{R.id.student_id,R.id.student_names,R.id.student_MacAddress});
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