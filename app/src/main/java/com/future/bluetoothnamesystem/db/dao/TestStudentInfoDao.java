package com.future.bluetoothnamesystem.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.future.bluetoothnamesystem.bean.StudentInfo;

import java.util.ArrayList;

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
}
