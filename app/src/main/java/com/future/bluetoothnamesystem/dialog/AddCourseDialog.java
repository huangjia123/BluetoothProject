package com.future.bluetoothnamesystem.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;

/**
 * Created by future on 2015/10/7.
 */
public class AddCourseDialog extends Dialog {

    private Context mContext;

    private EditText courseIdEdit,courseNameEdit;

    private Button confirm,cancel;
    private int courseId;
    private String courseIdStr,courseName;

    private TestCourseInfoDao testCourseInfoDao;

    public AddCourseDialog(Context context){
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
        setTitle("添加课程");
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

                courseIdStr = courseIdEdit.getText().toString();
                courseId = Integer.parseInt(courseIdStr);
                courseName = courseNameEdit.getText().toString();
                boolean b =  testCourseInfoDao.add(courseId,courseName);
                if (b){
                    Toast.makeText(mContext,"添加成功",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(mContext,"添加失败",Toast.LENGTH_LONG).show();
                }
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
