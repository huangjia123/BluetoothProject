package com.future.bluetoothnamesystem.activity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import com.future.bluetoothnamesystem.R;

/**
 * 作用：测试九宫格手势密码
 * 作者：佳佳
 * 时间：2015年8月25日 
 * */
public class SetPwdResActivity extends Activity {
	
	private TextView showInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_respasswordlock);
		showInfo = (TextView)findViewById(R.id.res_info);
		SharedPreferences shareDate = getSharedPreferences("GUE_PWD", 0);
		String pwd = shareDate.getString("GUE_PWD", "NO HAVE PWD !");
		showInfo.setTextSize(15);
		showInfo.setText("设置成功！您设置的手势密码是："+pwd);
	}
}
