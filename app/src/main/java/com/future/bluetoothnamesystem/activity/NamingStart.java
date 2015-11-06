package com.future.bluetoothnamesystem.activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.adapter.MyAdapter;
import com.future.bluetoothnamesystem.db.dao.BluetoothDao;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class NamingStart extends BaseActivity {
    Spinner spChooseClass,spChooseCourse;
    List<String> mClassesList,mCoursesList = new ArrayList<String>();
    String selectCourse;
    private String[] mItemsCallName = new String[]{"选择班级", "选择课程", "开始点名", "查看结果"};
    private ArrayList<String> mClassesChoosedList = new ArrayList<>();
    private GridView gv;
    private List<Boolean> mSelectedList;
    private MySelectAdapter adapter;
    private BluetoothAdapter adapter1;
    private ArrayAdapter mClassesChoosedAdapter,courseAdapter;
    private LinearLayout ll;
    private LinearLayout llClassChoose;
    private BlutetoothReceiver blutetoothReceiver;
    private List<String> list = new ArrayList<String>();
    private int flag=0;
    //从sharedPreferences中读出的课程
    private String sCourse;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    int courseid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_start);
        spChooseClass = (Spinner) findViewById(R.id.sp_choose_class);
        spChooseCourse= (Spinner) findViewById(R.id.sp_choose_course);
        sharedPreferences= getSharedPreferences("wujay", Context.MODE_PRIVATE); //私有数据
        editor= sharedPreferences.edit();//获取编辑器

        // 获得BluetoothAdapter对象，该API是android 2.0开始支持的
        adapter1 = BluetoothAdapter.getDefaultAdapter();
        // adapter不等于null，说明本机有蓝牙设备
        if (adapter1 != null) {
            Toast.makeText(NamingStart.this, "本机有蓝牙设备!", Toast.LENGTH_LONG).show();
            // System.out.println("本机有蓝牙设备！");
            // 如果蓝牙设备未开启
            if (!adapter1.isEnabled()) {
                Intent intent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                // 请求开启蓝牙设备
                startActivity(intent);
            }
        } else {
            Toast.makeText(NamingStart.this, "本机没有蓝牙设备!", Toast.LENGTH_LONG).show();
        }
        // 创建一个IntentFilter对象将其action指定为BluetoothDevice.ACTION_FOUND；
        IntentFilter intentFilter = new IntentFilter(
                BluetoothDevice.ACTION_FOUND);
        blutetoothReceiver = new BlutetoothReceiver();
        // 注册广播接收器
        registerReceiver(blutetoothReceiver, intentFilter);
        // 注册搜索完时的receiver
        intentFilter = new IntentFilter(
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(blutetoothReceiver, intentFilter);
        /*******************/
        //如果课程和班级为空则从存储中读取
        if(reader()!=null){
            mClassesChoosedList=reader();
            courseid = sharedPreferences.getInt("selectCourse", 0);
        }
        /*******************/
        initData();
        initView();
    }
    public void initView() {
        ll = (LinearLayout) findViewById(R.id.linearLayout);
        llClassChoose = (LinearLayout) findViewById(R.id.ll_class_choose);
        gv = (GridView) findViewById(R.id.gv_classes_choosed);
        spChooseClass.setAdapter(adapter);
        spChooseCourse.setAdapter(courseAdapter);
        /*****************************/
        //获取课程位置
        spChooseCourse.setSelection(courseid);
        /****************************/
        gv.setAdapter(mClassesChoosedAdapter);
    }
    public void initData() {
        TestStudentInfoDao siDao = new TestStudentInfoDao(NamingStart.this);
        mClassesList = siDao.findClass();
        TestCourseInfoDao tcDao=new TestCourseInfoDao(NamingStart.this);
        mCoursesList=tcDao.findAllCourseNames();
        adapter = new MySelectAdapter(NamingStart.this, mClassesList);
        courseAdapter=new ArrayAdapter(NamingStart.this,android.R.layout.simple_list_item_single_choice,mCoursesList);
        mClassesChoosedAdapter = new ArrayAdapter(NamingStart.this, R.layout.gridview_text, mClassesChoosedList);
    }
    class MySelectAdapter extends MyAdapter<String> {
        public MySelectAdapter(Context ctx, List<String> list) {
            super(ctx, list);
            mSelectedList = new ArrayList<Boolean>();
            for (int i = 0; i < mClassesList.size(); i++) {
                mSelectedList.add(false);
            }
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = View.inflate(NamingStart.this, R.layout.class_item_selected, null);
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.class_select);
            final String className = mClassesList.get(position).toString();
            checkBox.setText(className);
            checkBox.setChecked(mSelectedList.get(position));
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mSelectedList.set(position, true);
                        if (!mClassesChoosedList.contains(className)) {
                            mClassesChoosedList.add(className);
                            mClassesChoosedAdapter.notifyDataSetChanged();
                        }
                    } else {
                        mSelectedList.set(position, false);
                        mClassesChoosedList.remove(className);
                        mClassesChoosedAdapter.notifyDataSetChanged();
                    }
                }
            });
            return view;
        }
    }

    //开始点名
    public void goStart(View view){
        if(spChooseCourse.getSelectedItem()!=null&&mClassesChoosedList.size()!=0){

            selectCourse=spChooseCourse.getSelectedItem().toString();
            /***********************************************/
            courseid=spChooseCourse.getSelectedItemPosition();
            //把课程和班级传入
            Set<String>setchoosedList=new HashSet<String>(mClassesChoosedList);
            write(courseid,setchoosedList);
            /**************************************/
            if(selectCourse!=null){
                // Toast.makeText(NamingStart.this,"佳佳的功能",Toast.LENGTH_SHORT).show();
                //如果蓝牙开启
                if(adapter1.isEnabled()){
                    setProgressBarIndeterminateVisibility(true);
                    Toast.makeText(NamingStart.this,"正在扫描....",Toast.LENGTH_LONG).show();
                    // setTitle("正在扫描....");
                    // 如果正在搜索，就先取消搜索
                    if (adapter1.isDiscovering()) {
                        adapter1.cancelDiscovery();
                    }
                    // 开始搜索蓝牙设备,搜索到的蓝牙设备通过广播返回
                    adapter1.startDiscovery();
                }
            }
        }else{
            Toast.makeText(NamingStart.this,"请查看是否选中课程和班级",Toast.LENGTH_LONG).show();
        }
    }
    //查看此次点名结果
    public void goSearchResult(View view){
      if(flag==1){
          if(selectCourse!=null&&mClassesChoosedList.size()!=0){
              if(spChooseCourse.getSelectedItem()!=null){
                  selectCourse=spChooseCourse.getSelectedItem().toString();
                  //  Toast.makeText(NamingStart.this,"选择的课程"+selectCourse,Toast.LENGTH_LONG).show();
              }else{
                  Toast.makeText(NamingStart.this,"请选择课程",Toast.LENGTH_LONG).show();
              }
              Toast.makeText(NamingStart.this,"****"+selectCourse,Toast.LENGTH_LONG).show();
              Intent intent=new Intent(NamingStart.this,NamingResult.class);
              intent.putStringArrayListExtra("classname",mClassesChoosedList);
              intent.putExtra("course",selectCourse);
              startActivity(intent);
          }else{
              Toast.makeText(NamingStart.this,"请查看是否选中课程和班级",Toast.LENGTH_LONG).show();
          }
      }else{
          Toast.makeText(NamingStart.this,"点名未完成",Toast.LENGTH_LONG).show();
      }
}
    //创建广播接收者
    class BlutetoothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // 获得已经搜索到的蓝牙设备
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 搜索到的不是已经绑定的蓝牙设备
                if (device.getBondState()!= BluetoothDevice.BOND_BONDED) {
                    // 把扫描的mac地址发到list中
                    list.add(device.getAddress());
                }
                // 搜索完成
            } else if (action
                    .equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                setProgressBarIndeterminateVisibility(false);
                //setTitle("搜索蓝牙设备");
                Toast.makeText(NamingStart.this,"搜索蓝牙设备"+list.size()+selectCourse,Toast.LENGTH_LONG).show();
                flag=1;
                if(list.size()!=0&&selectCourse!=null){
                    Toast.makeText(NamingStart.this,"*******"+selectCourse+list.get(0),Toast.LENGTH_LONG).show();
                    BluetoothDao bluetoothDao=new BluetoothDao(NamingStart.this);
                    bluetoothDao.updateThisTime(selectCourse,list);
                }
            }
        }
    }
    //
    /*缓存数据
     *把获取的班级和课程信息存起来
     *
     * */
    public void write(int courseid,Set<String>set){
        editor.putInt("selectCourse", courseid);
        editor.putStringSet("mClassesChoosedList", set);
        editor.commit();//提交修改
    }
    /*读取数据
     * 把班级和课程信息取出来
     **/
    public ArrayList<String> reader(){
        Set<String> stringSet = sharedPreferences.getStringSet("mClassesChoosedList", null);
        if(stringSet==null){
            return null;
        }else{
            ArrayList<String> list=new ArrayList<String>(stringSet);
            return list;
        }
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(NamingStart.this, "我已停止，蓝牙点名搜索", Toast.LENGTH_SHORT).show();
        unregisterReceiver(blutetoothReceiver);
        super.onDestroy();
    }
}
