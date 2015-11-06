package com.future.bluetoothnamesystem.db.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by baiju on 2015/8/27.
 */
@Deprecated
public class BluetoothOpenHelper extends SQLiteOpenHelper {
    public BluetoothOpenHelper(Context context) {
        super(context, "bluetooth.db", null, 1);
    }

    public BluetoothOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
