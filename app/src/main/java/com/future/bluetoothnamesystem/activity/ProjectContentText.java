package com.future.bluetoothnamesystem.activity;

import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;

/**
 * 作用：测试九宫格手势密码
 * 作者：佳佳
 * 时间：2015年8月25日 
 * */
public class ProjectContentText extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//设置标题不显示
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passwordlock);
		

	
	}

}
