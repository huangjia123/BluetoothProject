package com.future.bluetoothnamesystem.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.future.bluetoothnamesystem.R;

/**
 * 自定义基类Activity
 * 继承该类之后，activy可以自带goBack的方法
 * 配合标题栏可实现返回功能
 * @author 王志成
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void goBack(View view){
        finish();
    }

}
