package com.future.bluetoothnamesystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;

/**
 * Created by Administrator on 2016/3/15.
 */
public class SetTeacherDialog extends Dialog {

    private Context mContext;

    private EditText courseIdEdit,courseNameEdit;
    private TextView teacherid,teacherName;
    private Button confirm,cancel;
    private int teacherId;
    private String teacherIdStr,teacherStrName;

    private TestCourseInfoDao testCourseInfoDao;

    
    public SetTeacherDialog(Context context){
        super(context);
        this.mContext = context;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursesetting_addcourse_dialog);
        setTitle("设置老师");
        teacherid=(TextView)findViewById(R.id.kechengorteacher);//教师ID号
        teacherName=(TextView)findViewById(R.id.coursenameotteacher);//老师姓名
        teacherid.setText("老师号");
        teacherName.setText("老师名");
        courseIdEdit = (EditText) findViewById(R.id.course_id);
        courseNameEdit = (EditText) findViewById(R.id.course_name);
        confirm = (Button) findViewById(R.id.addcourse_confirm);
        cancel = (Button) findViewById(R.id.addcourse_cancel);
        addListening();
        testCourseInfoDao = new TestCourseInfoDao(mContext);
    }

    public void addListening(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getContext().getSharedPreferences("asdf", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                teacherIdStr = courseIdEdit.getText().toString();

                teacherStrName=courseNameEdit.getText().toString();

                editor.putString("teaceridname", teacherIdStr);
                editor.putString("teacerteachername", teacherStrName);
                editor.commit();//提交修改
               Log.i("ASDF","提交陈宫"+sharedPreferences.getString("teaceridname",null));
                dismiss();
            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
