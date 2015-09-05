package com.future.bluetoothnamesystem.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.future.bluetoothnamesystem.bean.CourseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hehehe on 2015/8/27.
 */
public class TestCourseInfoDao {

    private final DataBaseHelper helper;

    public TestCourseInfoDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    /**
     * 添加数据
     * @param course_id 课程号
     * @param course_name 课程名
     * @return
     */
    public boolean add(int course_id, String course_name){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_id", course_id);
        values.put("course_name", course_name);
        long rowId = db.insert("course", null, values);

        if (rowId == -1){
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param course_id 根据课程号删除课程
     * @return
     */
    public boolean delete(String course_id){
        SQLiteDatabase db = helper.getReadableDatabase();
        int cursor = db.delete("course", "course_id = ?", new String[]{course_id});

        if (cursor == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 通过课程号修改课程名称
     * @param course_id
     * @param course_name
     * @return
     */
    public boolean update(String course_id, String course_name){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("course_name", course_name);
        int cursor = db.update("course", values, "course_id = ?", new String[]{course_id});

        if (cursor == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 根据课程号查询课程名称
     * @param course_id
     * @return
     */
    public String findName(String course_id){
        String course_name = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("course", new String[]{"course_name"}, "course_id = ?",
                new String[]{course_id}, null, null, null);
        if (cursor.moveToNext()){
            course_name = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return course_name;
    }

    /**
     * 查询所有课程号的课程名称
     * @return
     */
    public List<CourseInfo> findAllCourseName(){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
        Cursor cursor = db.query("course", new String[]{"course_id", "course_name"}, null, null, null, null, null);
        while(cursor.moveToNext()){

            CourseInfo courseInfo = new CourseInfo();

            courseInfo.setCourseId(cursor.getInt(0));
            courseInfo.setCourseName(cursor.getString(1));
            courseInfos.add(courseInfo);
        }
        cursor.close();
        db.close();
        return courseInfos;
    }
}
