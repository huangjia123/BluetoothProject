package com.future.bluetoothnamesystem.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.view.NinePointLineView;

/**
 * 作用：测试九宫格手势密码
 * 作者：佳佳
 * 时间：2015年8月25日 
 * */
public class PasswordLock extends BaseActivity {
	
	private LinearLayout nine_con;//九宫格容器
	
	NinePointLineView nv;//九宫格View
	
	TextView showInfo;
	
	boolean isSetFirst = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//设置标题不显示
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_passwordlock);
		
		nv = new NinePointLineView(PasswordLock.this);
		
		nine_con = (LinearLayout)findViewById(R.id.nine_con);
		
		nine_con.addView(nv);
		
		showInfo = (TextView)findViewById(R.id.show_set_info);
		
		getSetPwd();
	
	}

	
	/**
	 * 作用：获取现在密码的设置步骤
	 * 作者：佳佳
	 * 时间：2014年10月29日 14:20:36
	 * */
	public void getSetPwd(){
		
		SharedPreferences shareDate = getSharedPreferences("GUE_PWD", 0);
		
		isSetFirst = shareDate.getBoolean("IS_SET_FIRST", false);
		
		if(!isSetFirst){
			showInfo.setTextSize(15);
			showInfo.setText("请设置手势密码");
			
			shareDate.edit().clear().commit();
			
		}else{
			showInfo.setTextSize(15);
			showInfo.setText("请再次确认手势密码");
			
		}
		boolean is_second_error = shareDate.getBoolean("SECOND_ERROR", false);
		if(is_second_error){
			showInfo.setTextSize(15);

			showInfo.setText("密码不一致，请重新输入");
			
		}
		
	}
}
