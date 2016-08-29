package com.future.bluetoothnamesystem.activity;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.bean.StudentInfo;
import com.future.bluetoothnamesystem.db.dao.DataBaseHelper;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;
import com.future.bluetoothnamesystem.view.StuService;

import java.util.ArrayList;

import java.util.List;
public class ClassManagerActivity extends BaseActivity {
    DataBaseHelper helper;
    private String[] name = new String[]{"计科1", "计科1", "计科1", "计科1", "计科1",
            "计科1", "计科1", "计科1", "计科1"};
    private List<String> classNames;
    private List<String> stuNumbers;
    private ListView list;
    private String delete_className;
    private ArrayAdapter<String> adapter;
    private TestStudentInfoDao tsiDao;
    private int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);
        iniData();
        initView();
    }

    public void iniData() {
        tsiDao = new TestStudentInfoDao(ClassManagerActivity.this);
        classNames = tsiDao.findClass();
        //提示是否classNames为空
        //Toast.makeText(ClassManagerActivity.this, classNames.isEmpty() + "", Toast.LENGTH_SHORT).show();
        adapter = new ArrayAdapter<String>(this, R.layout.classes_names_list_view,
                R.id.etvnames_item, classNames);
    }

    public void initView() {
        list = (ListView) findViewById(R.id.lv_classNames);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String className = classNames.get(position);
                Intent it = new Intent(ClassManagerActivity.this, ClassesNamesActivity.class);
                it.putExtra("className", className);
                startActivity(it);
            }
        });
        //ListView长按删除班级
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @TargetApi(Build.VERSION_CODES.CUPCAKE)
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int arg2, long arg3) {
                AlertDialog alert =
                        new AlertDialog.Builder(ClassManagerActivity.this).create();
                alert.setIcon(R.drawable.notification_template_icon_bg);
                alert.setTitle("删除班级:");
                alert.setMessage("是否删除？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ClassManagerActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ClassManagerActivity.this, "确定", Toast.LENGTH_SHORT).show();
                        tsiDao.deleteClass(classNames.get(arg2));
                        classNames.remove(arg2);
                        adapter.notifyDataSetChanged();
                    }
                });
                alert.show();
                return true;
            }
        });
    }

    public void add(View view) {
        Toast.makeText(ClassManagerActivity.this, "可以用", Toast.LENGTH_SHORT).show();
        pao();
    }
    //导入班级
  final  StuService service=new StuService(this);
    DataBaseHelper db=new DataBaseHelper(this);
    /**
     * 获取表格中的数据*/
    public  void pao() {
        //得到表格中所有的数据
        List<StudentInfo> listExcel= StuService.getAllByExcel(Environment.getExternalStorageDirectory().getPath()+"/books.xls");
        Log.e("-----------",Environment.getExternalStorageDirectory().getPath()+"***"+listExcel);

        ContentValues values=new ContentValues();
        for (StudentInfo stuEntity : listExcel) {
            String stu_id=stuEntity.getStuId();
            if (!service.isExist(stu_id)){
                Toast.makeText(ClassManagerActivity.this,"有没人嫩告诉你有",Toast.LENGTH_LONG).show();
                //不存在就添加
                values.put("stu_id", stuEntity.getStuId());
                values.put("stu_name", stuEntity.getStuName());
                values.put("macAddress", stuEntity.getMacAddress());
                values.put("class_name", stuEntity.getClassName());
                db.AddU(values);
                Log.e("-----------","&&&&&&&&&&&&&&&&");
            }else{
                Toast.makeText(ClassManagerActivity.this,"存在了",Toast.LENGTH_LONG).show();
                //存在就更新
                values.put("stu_id",stuEntity.getStuId());
                values.put("stu_name", stuEntity.getStuName());
                values.put("macAddress", stuEntity.getMacAddress());
                values.put("class_name", stuEntity.getClassName());
                db.AddU(values);
                Log.e("-----------", "################");
            }
        }
    }
    //删除班级
    public void delete(View view) {
        if (!classNames.isEmpty()) {
            ArrayList<String> cn = (ArrayList<String>) classNames;
            final String[] names = new String[cn.size()];
            for (int i = 0; i < cn.size(); i++) {
                names[i] = cn.get(i);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(ClassManagerActivity.this);
            builder.setTitle("请选择您想要删除的班级：");
            builder.setSingleChoiceItems(names, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    delete_className = names[which];
                    index = which;
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (index != -1) {
                        tsiDao.deleteClass(delete_className);
                        delete_className = null;
                        classNames.remove(index);
                        index = -1;
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ClassManagerActivity.this, "您没有选中！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            builder.create().show();
        } else {
            Toast.makeText(ClassManagerActivity.this, "班级为空！", Toast.LENGTH_SHORT).show();
        }
    }

}
