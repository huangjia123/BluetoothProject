package com.future.bluetoothnamesystem;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.content.Context;
import android.test.AndroidTestCase;

import com.future.bluetoothnamesystem.bean.StudentInfo;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends AndroidTestCase {

    Context mContext;

    private String[] cuorseName = new String[]{"大学英语", "高数", "思修", "近代史", "大学物理",
            "计算机导论", "操作系统", "计算机组成原理与接口技术", "数据库原理", "信息安全"};

    @Override
    public void setUp() throws Exception {
        this.mContext = getContext();
        super.setUp();
    }

    public void testAdd(){
        TestCourseInfoDao testCourseDao = new TestCourseInfoDao(mContext);
        for (int i = 0; i < cuorseName.length; i++) {
            boolean add = testCourseDao.add(1001 + i, cuorseName[i]);
            System.out.println(add);
        }
    }

    public void testAddStuInfo(){

        TestStudentInfoDao tsd=new TestStudentInfoDao(mContext);
         StudentInfo  stu1=new StudentInfo("1001","王志成","100120","计科121");
         StudentInfo  stu2=new StudentInfo("1002","刘志成","100110","计科122");
         StudentInfo  stu3=new StudentInfo("1003","李志成","1001201","计科121");
        tsd.add(stu1);
        tsd.add(stu2);
        tsd.add(stu3);

    }

    public void testFindClass(){
        TestStudentInfoDao dao=new TestStudentInfoDao(mContext);
        List list=dao.findClass();
        System.out.println(list.size()+"-----------===============----------------");
    }


    public void testDelete(){
        TestCourseInfoDao testCourseDao = new TestCourseInfoDao(mContext);
        boolean delete = testCourseDao.delete("1004");
        assertEquals(true, delete);
    }

    public void testUpdate(){
        TestCourseInfoDao testCourseInfoDao = new TestCourseInfoDao(mContext);
        boolean update = testCourseInfoDao.update(1006 + "", "毛概");

    }

    public void testFind(){
        TestCourseInfoDao testCourseInfoDao = new TestCourseInfoDao(mContext);
        String courseName = testCourseInfoDao.findName("1006");
        System.out.println(courseName);
    }

}

