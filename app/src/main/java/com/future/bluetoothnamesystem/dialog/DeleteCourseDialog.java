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
 * Created by Administrator on 2015/10/15.
 */
public class DeleteCourseDialog extends Dialog {
    private Context mContext;
    private EditText courseIdEdit;
    private Button confirm,cancel;
    private int courseId;
    private String courseIdStr;
    private TestCourseInfoDao testCourseInfoDao;
    public DeleteCourseDialog(Context context){
        super(context);
        this.mContext = context;
    }
    //删除课程名单
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursesetting_deletecourse_dialog);
        setTitle("删除课程");
        courseIdEdit = (EditText) findViewById(R.id.deletecourse_id);
        confirm = (Button) findViewById(R.id.deletecourse_confirm);
        cancel = (Button) findViewById(R.id.deletecourse_cancel);
        deleteListening();
        //创建课程信息设置
        testCourseInfoDao = new TestCourseInfoDao(mContext);
    }
    //删除课程监听
    public void deleteListening(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseIdStr = courseIdEdit.getText().toString();
                boolean b = testCourseInfoDao.delete(courseIdStr);
                if (b) {
                    Toast.makeText(mContext, "删除成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "删除失败", Toast.LENGTH_LONG).show();
                }
                dismiss();
            }
        });
        //调用取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
