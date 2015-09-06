package com.future.bluetoothnamesystem.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.future.bluetoothnamesystem.bean.NamingRecard;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by baiju on 2015/8/27.
 */
public class BluetoothDao {
    public BluetoothOpenHelper helper;

    public BluetoothDao(Context context) {
        helper = new BluetoothOpenHelper(context);
    }

    public boolean addNamingRecard(NamingRecard namingRecard) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", namingRecard.getRec_id());
        contentValues.put("stu_id", namingRecard.getStu_id());
        contentValues.put("course_name", namingRecard.getCourse());
        contentValues.put("stu_name", namingRecard.getName());
        contentValues.put("teacher_name", namingRecard.getTeacherName());
        contentValues.put("arrival", namingRecard.getArrival());
        contentValues.put("non_arrival", namingRecard.getNon_arrival());
        contentValues.put("late", namingRecard.getLate());
        contentValues.put("break", namingRecard.getBreaks());
        contentValues.put("this_time", namingRecard.getThisTime());
        contentValues.put("class_name", namingRecard.getClassName());
        long rowid = db.insert("naming_record", null, contentValues);
        if (rowid == 1) {
            return false;
        } else {
            return true;
        }
    }

    public List<NamingRecard> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        List<NamingRecard> namingRecardList = new ArrayList<NamingRecard>();
        Cursor cursor = db.query("naming_record", new String[]{"id", "stu_id", "course_name", "stu_name", "teacher_name",
                "arrival", "non_arrival", "late", "break", "this_time", "class_name", "naming_record"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            NamingRecard namingRecard = new NamingRecard();
            namingRecard.setRec_id(cursor.getInt(0));
            namingRecard.setStu_id(cursor.getLong(1));
            namingRecard.setCourse(cursor.getString(2));
            namingRecard.setName(cursor.getString(3));
            namingRecard.setTeacherName(cursor.getString(4));
            namingRecard.setArrival(cursor.getString(5));
            namingRecard.setNon_arrival(cursor.getString(6));
            namingRecard.setLate(cursor.getString(7));
            namingRecard.setBreaks(cursor.getString(8));
            namingRecard.setThisTime(cursor.getString(9));
            namingRecard.setClassName(cursor.getString(10));
            namingRecardList.add(namingRecard);
        }

        cursor.close();
        db.close();
        return namingRecardList;

    }

    public List<Map<String, String>> findClass() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Map<String, String>> group = new ArrayList<Map<String, String>>();
        Cursor cursor = db.query("naming_record", new String[]{"class_name", "count(distinct stu_id)"}, null, null, "class_name", null, null);
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("group", cursor.getString(0));
            map.put("stuCount", cursor.getString(1));
            group.add(map);

        }
        cursor.close();
        db.close();
        return group;
    }


    /**
     * 根据训中的课程 ，以及班级，，查找学生信息
     *
     * @param className  班级名
     * @param courseName 课程名
     * @return
     */
    public List<Map<String, String>> findItem(String className, String courseName) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Map<String, String>> namingResult = new ArrayList<Map<String, String>>();
        Cursor cursor = db.query("naming_record", new String[]{"id", "stu_id", "course_name",
                        "stu_name", "teacher_name", "arrival", "non_arrival", "late", "break", "this_time",
                        "class_name"}, "class_name=? and course_name=?", new String[]{className, courseName},
                null, null, null);
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            NamingRecard namingRecard = new NamingRecard();
            map.put("stu_id", cursor.getString(1).toString());
            map.put("stu_name", cursor.getString(3));

            namingResult.add(map);
        }

        return namingResult;
    }

    public List<Map<String, Object>> findNoComingResult(String group, String courseName) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Map<String, Object>> namingResult = new ArrayList<Map<String, Object>>();

        Cursor cursor = db.query("naming_record", new String[]{"id", "stu_id", "course_name",
                        "stu_name", "teacher_name", "arrival", "non_arrival", "late", "break", "this_time",
                        "class_name"}, "class_name=? and course_name=?",
                new String[]{group, courseName}, null, null, null);

        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("stu_id", cursor.getString(1).toString());
            map.put("stu_name", cursor.getString(3));
            map.put("this_time", cursor.getString(9));
//            switch (cursor.getString(9)) {
//                case "0":
//                    map.put("late", true);
//                    map.put("non_arrival", false);
//                    map.put("break", false);
//                    break;
//                case "1":
//                    map.put("later", false);
//                    map.put("non_arrival", true);
//                    map.put("break", false);
//                    break;
//                case "2":
//                    map.put("later", false);
//                    map.put("non_arrival", false);
//                    map.put("break", true);
//                    break;
//                default:
//                    break;
//
//            }
            namingResult.add(map);
        }

        return namingResult;
    }

    public boolean update(String stuId,String courseName,String choice) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("this_time", choice);

        int i = db.update("naming_record", contentValues, "stu_id=? and course_name=?", new String[]{stuId,courseName});
        if (i > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<Map<String, NamingRecard>> findNamingResult(String Group) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Map<String, NamingRecard>> namingResult = new ArrayList<Map<String, NamingRecard>>();

        Cursor cursor = db.query("naming_record", new String[]{"id", "stu_id", "course_name",
                "stu_name", "teacher_name", "arrival", "non_arrival", "late", "break", "this_time",
                "class_name", "naming_record"}, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Map<String, NamingRecard> map = new HashMap<String, NamingRecard>();
            NamingRecard namingRecard = new NamingRecard();
            namingRecard.setRec_id(cursor.getInt(0));
            namingRecard.setStu_id(cursor.getLong(1));
            namingRecard.setCourse(cursor.getString(2));
            namingRecard.setName(cursor.getString(3));
            namingRecard.setTeacherName(cursor.getString(4));
            namingRecard.setArrival(cursor.getString(5));
            namingRecard.setNon_arrival(cursor.getString(6));
            namingRecard.setLate(cursor.getString(7));
            namingRecard.setBreaks(cursor.getString(8));
            namingRecard.setThisTime(cursor.getString(9));
            namingRecard.setClassName(cursor.getString(10));
            // namingRecardList.add(namingRecard);
            map.put("namingResult", namingRecard);
            namingResult.add(map);
        }

        return namingResult;
    }
}
