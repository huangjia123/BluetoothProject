package com.future.bluetoothnamesystem.activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.activity.base.SetCourseActivity;
import com.future.bluetoothnamesystem.bean.UserRecord;
import com.future.bluetoothnamesystem.db.dao.BluetoothDao;
import com.future.bluetoothnamesystem.db.dao.DataBaseHelper;
import com.future.bluetoothnamesystem.view.LineGridView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
public class SettingActivity extends BaseActivity {
    private LineGridView  gvSet;
    private String[] mItems = new String[]{"课程设置","班级管理", "导出数据", "恢复数据",
            "教师信息", "软件简介"};
    private int[] mPics = new int[] { R.mipmap.set,R.mipmap.manage,
            R.mipmap.ban,R.mipmap.car,R.mipmap.sigh,R.mipmap.lock};
    String teacherIdStr;
    String teacherStrName;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedPreferences=getSharedPreferences("asdf", Context.MODE_PRIVATE);
        gvSet = (LineGridView) findViewById(R.id.gv_setting);
        gvSet.setAdapter(new HomeAdapter());
        gvSet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        //
                        startActivity(new Intent(SettingActivity.this, SetCourseActivity.class));
                        break;
                    case 1:
                        //
                        startActivity(new Intent(SettingActivity.this, ClassManagerActivity.class));
                        break;
                    case 2:
                        //
                        startActivity(new Intent(SettingActivity.this, ExportData.class));
                        break;
                    case 3:
                        restoreData(sharedPreferences.getString("teacerteachername", null));
                        break;
                    case 4:
                        //设置教师使用者的信息
                       View view1= (LinearLayout)getLayoutInflater().inflate(R.layout.teachersetting_addteacher_dialog,null);
                       if(!sharedPreferences.equals("")){
                           teacherIdStr= sharedPreferences.getString("teaceridname", null);
                           teacherStrName= sharedPreferences.getString("teacerteachername", null);
                           TextView teacherid=(TextView)view1.findViewById(R.id.course_id);
                           TextView teachername=(TextView)view1.findViewById(R.id.course_name);
                           teacherid.setText(teacherIdStr);
                           teachername.setText(teacherStrName);
                           AlertDialog.Builder alerDialog=new AlertDialog.Builder(SettingActivity.this);
                           alerDialog.setTitle("教师信息")
                                   .setView(view1)
                                   .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {

                                           dialog.dismiss();
                                       }
                                   }).create().show();
                       }else{
                           Toast.makeText(SettingActivity.this,
                                   "你还没添加老师信息！",
                                   Toast.LENGTH_SHORT).show();
                       }



                        break;
                    case 5:
                        //
                        startActivity(new Intent(SettingActivity.this,ProjectContentText.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    class HomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mItems.length;
        }
        @Override
        public Object getItem(int position) {
            return mItems[position];
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(SettingActivity.this,
                    R.layout.activity_setting2, null);
            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            tvItem.setText(mItems[position]);
            ivItem.setImageResource(mPics[position]);
            return view;
        }

    }
    BluetoothDao bluetoothDao=new BluetoothDao(this);
    DataBaseHelper helper = new DataBaseHelper(SettingActivity.this);
    //恢复数据
    public void restoreData(String teacherNameId){
        String url="http://10.1.24.26:8080/serverduan/HttpServletWeb2";
        RequestParams requestParams = new RequestParams();
        JSONObject json=new JSONObject();
        try {
            json.put("teacher_name", teacherNameId);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String str = json.toString();
        requestParams.put("TeachNameId", str);
        new AsyncHttpClient().post(url, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String returnParam) {
                int F=1;
                if(returnParam!=null){
                    String[] returnParams=returnParam.split("/");
                    SQLiteDatabase db =helper.getWritableDatabase();
                    for(int a=0;a<returnParams.length;a++){
                        Gson gson=new Gson();
                        UserRecord userRecord=gson.fromJson(returnParams[a], UserRecord.class);
                        String stuId=userRecord.getStu_id();
                        String courseName=userRecord.getCourse_name();
                        String className=userRecord.getClass_name();
                        String stuName=userRecord.getStu_name();
                        String teachName=userRecord.getTeacher_name();
                        int arrival=userRecord.getArrival();
                        int nonArrival=userRecord.getNon_arrival();
                        int late=userRecord.getLate();
                        int breaks=userRecord.getBreaks();
                        int thisTime=userRecord.getThis_time();
                        /*******************************/
                        Cursor cursor= bluetoothDao.selectTeacher(teachName, stuId);
                        if(cursor.moveToNext()){
                            Toast.makeText(SettingActivity.this,"已经存在了",Toast.LENGTH_LONG).show();
                        }else {
                            String  sql12="insert into naming_record(id,stu_id,course_name"
                                    + ",class_name,stu_name,teacher_name,arrival,non_arrival,late,break,this_time)"
                                    + " VALUES (null,'"+stuId+"','"+courseName+"','"+className+"','"+
                                    stuName+"','"+teachName+"','"+arrival+"','"+nonArrival+"','"+late+"','"+breaks+"','"+thisTime+"')";
                            db.execSQL(sql12);
                            Toast.makeText(SettingActivity.this,"成功同步",Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Toast.makeText(SettingActivity.this,"还没数据!",Toast.LENGTH_LONG).show();

                }

            }
            @Override
            public void onFailure(Throwable throwable, String s) {
                super.onFailure(throwable, s);
            }
        });
    }
}



