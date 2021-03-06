package com.future.bluetoothnamesystem.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.adapter.MyAdapter;
import com.future.bluetoothnamesystem.db.dao.DataBaseHelper;
import com.future.bluetoothnamesystem.dialog.SetTeacherDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private GridView gvSet1;
    private String[] mItemsCallName = new String[]{"在线点名", "查看结果", "查看所有记录", "上传数据"};
    private List<String> list = new ArrayList<String>();
    private int[] mPicsCallName = new int[]{R.mipmap.horn, R.mipmap.good,
            R.mipmap.write, R.mipmap.v};
    private LinearLayout lin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=getSharedPreferences("asdf", Context.MODE_PRIVATE);
        if(!sharedPreferences.equals("")&&sharedPreferences.getString("teaceridname",null)==null){
            SetTeacherDialog setTeacherDialog=new SetTeacherDialog(MainActivity.this);
            setTeacherDialog.show();
        }

        lin = (LinearLayout) findViewById(R.id.linearLayout);
        for (int i = 0; i < 4; i++) {
            list.add(mItemsCallName[i]);
        }
        gvSet1 = (GridView) findViewById(R.id.gv_setting_choose);
        InnerAdapter adapter = new InnerAdapter(MainActivity.this, list);
        gvSet1.setAdapter(adapter);
        gvSet1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this,NamingStart.class));
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"王志成修改",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,NamingResult.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,CheckResultActivity.class));
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"上传数据中",Toast.LENGTH_SHORT).show();
                        try{
                           findListener();
                        }catch(JSONException e){

                        }
                        break;
                }
            }
        });
    }

    public void goSet(View view){
        startActivity(new Intent(MainActivity.this,SettingActivity.class));
    }
    class InnerAdapter extends MyAdapter<String> {

        public InnerAdapter(Context ctx, List<String> list) {
            super(ctx, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
            if (convertView == null) {
                convertView = mInflater.inflate(
                        R.layout.item_select, parent, false);
                holder = new ViewHolder();
                holder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
                holder.ivItem = (ImageView) convertView.findViewById(R.id.iv_item);
                ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
                layoutParams.height = lin.getHeight() / 2;
                convertView.setLayoutParams(layoutParams);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (convertView.getHeight() == 0) {
                ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();

                layoutParams.height = lin.getHeight() / 2;
                convertView.setLayoutParams(layoutParams);
            }
            holder.tvItem.setText(list.get(position).toString());
            holder.ivItem.setImageResource(mPicsCallName[position]);
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tvItem;
        ImageView ivItem;
    }


    public DataBaseHelper helper = new DataBaseHelper(this);
    //上传数据客户端
    public void findListener() throws JSONException {
        String url="http://10.1.24.26:8080/serverduan/HttpServletWeb";
        RequestParams requestParams = new RequestParams();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("naming_record", new String[]{"_id", "stu_id", "course_name",
                "class_name", "stu_name", "teacher_name", "arrival", "non_arrival", "late", "break", "this_time"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            if(cursor.getInt(10)==0){
                JSONObject json=new JSONObject();
                json.put("stu_id", cursor.getString(1));
                json.put("course_name", cursor.getString(2));
                json.put("class_name", cursor.getString(3));
                json.put("stu_name", cursor.getString(4));
                json.put("teacher_name", cursor.getString(5));
                json.put("arrival", cursor.getInt(6));
                json.put("non_arrival", cursor.getInt(7));
                json.put("late", cursor.getInt(8));
                json.put("breaks", cursor.getInt(9));
                json.put("this_time", cursor.getInt(10));
                String str = json.toString();
                requestParams.put("namerecord",str);
                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(6*1000);
                client.post(url, requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, String arg1) {
                        Toast.makeText(MainActivity.this,"上传成功",Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
            }
        }
        cursor.close();
        db.close();
    }
}
