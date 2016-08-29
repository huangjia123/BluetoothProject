package com.future.bluetoothnamesystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.future.bluetoothnamesystem.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MainPageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpageactivity);
        Thread thread=new Thread(){
            public void run(){
                try {
                    Thread.sleep(5000);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                Intent intent=new Intent(MainPageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        thread.start();
    }
}
