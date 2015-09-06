package com.future.bluetoothnamesystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;

public class ClassManagerActivity extends BaseActivity {

    private String[] name = new String[]{"计科1", "计科1", "计科1", "计科1", "计科1",
            "计科1", "计科1", "计科1", "计科1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.classes_names_list_view,
                R.id.etvnames_item, name);
        ListView list = (ListView) findViewById(R.id.lv_classNames);
        list.setAdapter(adapter);
    }

    public void add(View view){
        Toast.makeText(ClassManagerActivity.this, "可以用", Toast.LENGTH_SHORT).show();
    }
}
