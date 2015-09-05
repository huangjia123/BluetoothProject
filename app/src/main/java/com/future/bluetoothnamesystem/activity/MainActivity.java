package com.future.bluetoothnamesystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open1(View view){
        Intent intent =new Intent(MainActivity.this,ClassManagerActivity.class);
        startActivity(intent);
    }

    public void open2(View view){
        Intent intent =new Intent(MainActivity.this,ClassesNamesActivity.class);
        startActivity(intent);
    }

    public void open3(View view){
        Intent intent =new Intent(MainActivity.this,CheckResultActivity.class);
        startActivity(intent);
    }

}
