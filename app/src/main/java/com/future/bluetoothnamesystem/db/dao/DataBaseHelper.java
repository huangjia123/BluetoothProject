package com.future.bluetoothnamesystem.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hehehe on 2015/8/27.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    /**
     * dataBaseName数据库名
     */
    private static final String dataBaseName = "namingSystem.db";
    /**
     * version数据库版本号
     */
    private static final int version = 1;

    /**
     * tableName1课程信息表
     */
    private static final String tableName1 = "course";
    /**
     * tableName2学生信息表
     */
    private static final String tableName2 = "student_information";

    public DataBaseHelper(Context context) {
        super(context, dataBaseName, null, version);
    }

    /**
     * 见表并初始化
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String table1 = "CREATE TABLE course (course_id INT(5) PRIMARY KEY,course_name VARCHAR( 10 ) NOT NULL);";
        db.execSQL(table1);

        String table2 = "CREATE TABLE student_information (stu_id BIGINT(15) PRIMARY KEY,stu_name VARCHAR( 5 )," +
                "macAddress VARCHAR(30),  class_name VARCHAR(10));";
        db.execSQL(table2);

        db.execSQL("CREATE TABLE naming_record (" +
                " id           INTEGER( 15 )  PRIMARY KEY" +
                " NOT NULL" +
                " UNIQUE," +
                " stu_id       VARCHAR( 15 )," +
                " course_name  VARCHAR( 15 )," +
                " class_name   VARCHAR( 15 )," +
                " stu_name     VARCHAR( 15 )," +
                " teacher_name VARCHAR( 15 )," +
                " arrival      INT( 3 )," +
                " non_arrival  INT( 3 )," +
                " late         INT( 3 )," +
                " break        INT( 3 )," +
                " this_time    INT( 2 )" +
                " );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
