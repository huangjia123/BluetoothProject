package com.future.bluetoothnamesystem.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.future.bluetoothnamesystem.bean.ClassInfo;
import com.future.bluetoothnamesystem.bean.StudentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hehehe on 2015/8/28.
 */
public class TestStudentInfoDao {

    public final DataBaseHelper helper;

    StudentInfo studentInfo = new StudentInfo();

    public TestStudentInfoDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    /**
     * 添加学生
     * @param studentInfo
     * @return
     */
    public boolean add(StudentInfo studentInfo){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("stu_id",studentInfo.getStuId()+"");
        values.put("stu_name", studentInfo.getStuName());
        values.put("macAddress", studentInfo.getMacAddress());
        values.put("class_name", studentInfo.getClassName());
        long rowId = db.insert("student_information", null, values);

        if (rowId == -1){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据学号删除学生
     * @param stuId
     * @return
     */
    public boolean delete(String stuId){
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowId = db.delete("student_information", "stu_id = ?", new String[]{stuId});
        if (rowId == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据学号修改学生信息
     * @param stuId
     * @return
     */
    public boolean update(String stuId){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stu_name", studentInfo.getStuName());
        values.put("macAddress", studentInfo.getMacAddress());
        values.put("class_name", studentInfo.getClassName())  ;
        int rowId = db.update("student_information", values, "stu_id = ?", new String[]{stuId});
        if (rowId == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据学号查询学生信息
     * @param stuId
     * @return
     */
    public String findInfo(String stuId){
        String stuName = "";
        String macAddress = "";
        String className = "";


        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("student_information", new String[]{"stu_name", "macAddress", "class_name"},
                "stu_id = ?", new String[]{stuId}, null, null, null);

        if(cursor.moveToNext()){
            stuName = cursor.getString(0);
            macAddress = cursor.getString(1);
            className = cursor.getString(2);
        }
        cursor.close();
        db.close();
        return stuId +"\t" + stuName + "\t" + macAddress + "\t" + className;
    }

    /**
     * 查询所有的学生信息
     * @return
     */
    public ArrayList<StudentInfo> findAllStu(){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<StudentInfo> studentInfos = new ArrayList<StudentInfo>();
        Cursor cursor = db.query("student_information", new String[]{"stu_id", "stu_name", "macAddress", "class_name"},
                null, null, null, null, null);
        while(cursor.moveToNext()){

            studentInfo.setStuId(cursor.getString(0));
            studentInfo.setStuName(cursor.getString(1));
            studentInfo.setMacAddress(cursor.getString(2));
            studentInfo.setClassName(cursor.getString(3));
            studentInfos.add(studentInfo);
        }

        return studentInfos;
    }
    /**
     * 查询班级信息
     * 包括班级名，班级名，班级学生数量
     */
    public List<String> findClass() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<String> group = new ArrayList<String>();
        Cursor cursor = db.query("student_information", new String[]{"class_name", "count(distinct class_name)"}, null, null, "class_name", null, null);
        while (cursor.moveToNext()) {

            String className=cursor.getString(0);

            group.add(className);

        }
        cursor.close();
        db.close();
        return group;
    }

    /**查询班级信息*/
    public List<ClassInfo> findClassAndCount() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<ClassInfo> group = new ArrayList<ClassInfo>();
        Map<String,Integer> classNameAndCountMap=new HashMap<String,Integer>();
        Cursor cursor = db.query("student_information", new String[]{"class_name", "count(distinct stu_id)"}, null, null, "class_name", null, null);
        while (cursor.moveToNext()) {
            ClassInfo ci=new ClassInfo(cursor.getString(1),cursor.getInt(0));
           // String className=cursor.getString(0);
            classNameAndCountMap.put(cursor.getString(1),cursor.getInt(0));

            group.add(ci);

        }
        cursor.close();
        db.close();
        return group;
    }

    /**
     * 查询课程查找班级名
     * 返回值为存放班级名的list集合
     */
    public List<String> findClassByCoursename(String courseName) {
        SQLiteDatabase db = helper.getReadableDatabase();
        /**
         * distinct class_name去除查询到的重复项
         * select distinct class_name  from naming_record;
         */
        Cursor cursor =
                db.query("naming_record", new String[]{"distinct class_name"}, "course_name = ? ", new String[]{courseName}, null, null, null);
        List<String> group = new ArrayList<String>();


        while (cursor.moveToNext()) {

            String className = cursor.getString(0);

            group.add(className);

        }
        cursor.close();
        db.close();
        return group;
    }

//王少峰******************************************************
    /**删除班级信息*/
    public void deleteClass(){
        SQLiteDatabase db = helper.getReadableDatabase();
        //删除SQL语句
        String sql = "delete from stu_table where _id  = 6";
        //执行SQL语句
        db.execSQL(sql);
    }
//王少峰******************************************************
}
