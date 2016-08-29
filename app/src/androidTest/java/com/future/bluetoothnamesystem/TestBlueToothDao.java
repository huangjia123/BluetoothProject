package com.future.bluetoothnamesystem;

import android.content.Context;
import android.test.AndroidTestCase;

import com.future.bluetoothnamesystem.bean.NamingRecard;
import com.future.bluetoothnamesystem.db.dao.BluetoothDao;

/**
 * Created by baiju on 2015/8/27.
 */
public class TestBlueToothDao extends AndroidTestCase{
    public Context mContext;
    public String[] stuName={"王志成","李志强"};
    public String[] teacherName={"李白","白居易"};
    public String[] courseName={"英语","高等数学","C语言","概率论","数据库","离散数学","近代史"};
    public String[] stuId={"20121514101","20121514102","20121514103","20121514104","20121514105",
            "20121514106","20121514107","20121514108","20121514109","20121514201","20121514202",
            "20121514203","20121514204","20121514205","20121514206","20121514207","20121514208",
            "20121514209"};
    public String[] className={"计科121","计科122"};
    @Override
    public void setUp() throws Exception {
        this.mContext=getContext();
        super.setUp();
    }
    public void testAdd() throws Exception {
        BluetoothDao dao=new BluetoothDao(mContext);
        NamingRecard namingRecord=new NamingRecard();
        int m=0;
        for(int i=0;i<stuName.length;i++){
            for(int j=0;j<teacherName.length;j++){
                namingRecord.setRec_id(m++);
                namingRecord.setStu_id(Long.parseLong(stuId[i]));
                namingRecord.setName(stuName[i]);
                namingRecord.setTeacherName(teacherName[j]);
                namingRecord.setCourse(courseName[j]);
                System.out.println("i==="+i+"j======"+j);
                if(i<=8) {
                    namingRecord.setClassName("计科121");
                }else{
                    namingRecord.setClassName("计科122");
                }
                namingRecord.setArrival(19+i+"");
                namingRecord.setNon_arrival(i + "");
                namingRecord.setLate(4 + "");
                namingRecord.setBreaks((1 + i) + "");
                namingRecord.setThisTime(0 + "");
                dao.addNamingRecard(namingRecord);
            }
        }
    }


}
