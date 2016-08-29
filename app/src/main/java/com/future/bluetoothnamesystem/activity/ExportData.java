package com.future.bluetoothnamesystem.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.future.bluetoothnamesystem.R;
import com.future.bluetoothnamesystem.activity.base.BaseActivity;
import com.future.bluetoothnamesystem.db.dao.DataBaseHelper;
import com.future.bluetoothnamesystem.db.dao.TestCourseInfoDao;
import com.future.bluetoothnamesystem.db.dao.TestStudentInfoDao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportData extends BaseActivity {

   private DataBaseHelper mDb = new DataBaseHelper(this);
    SQLiteDatabase db;
    Spinner spChooseClass, spChooseCourse, getSpChoosePath;
    List<String> mClassesList, mCoursesList = new ArrayList<String>();
    private ArrayAdapter mClassesAdapter, mCourseAdapter;


    String selectedClass, selectedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_data);
        spChooseClass = (Spinner) findViewById(R.id.sp_choose_class);
        spChooseCourse = (Spinner) findViewById(R.id.sp_choose_course);
       // getSpChoosePath = (Spinner) findViewById(R.id.sp_choose_location);
        db = mDb.getReadableDatabase();

        initData();
    }

    public void initData() {

        TestStudentInfoDao siDao = new TestStudentInfoDao(ExportData.this);
        mClassesList = siDao.findClass();
        TestCourseInfoDao tcDao = new TestCourseInfoDao(ExportData.this);
        mCoursesList = tcDao.findAllCourseNames();

        mClassesAdapter = new ArrayAdapter(ExportData.this,
                android.R.layout.simple_list_item_single_choice, mClassesList);
        mCourseAdapter = new ArrayAdapter(ExportData.this,
                android.R.layout.simple_list_item_single_choice, mCoursesList);
        spChooseClass.setAdapter(mClassesAdapter);
        spChooseCourse.setAdapter(mCourseAdapter);
    }

    //确定导出
    public void ensureExport(View view) {
        selectedClass = spChooseClass.getSelectedItem().toString();
        selectedCourse = spChooseCourse.getSelectedItem().toString();
       // selectedPath = spChooseClass.getSelectedItem().toString();

        WritableWorkbook book = null;
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {

            sdDir = Environment.getExternalStorageDirectory().getAbsoluteFile(); // 优先存在手机存储的目录下
            Log.e("-------", sdDir + "");
            if (!sdDir.exists()) {
                sdDir.mkdirs();
            }
            String fileName = selectedClass + selectedCourse + "点名结果.xls";
            File childFile = new File(sdDir, fileName);
            try {
                Cursor cur = db.query("naming_record",new String[]{ "stu_id, stu_name, arrival, non_arrival, late, break," +
                        " this_time"}, "class_name = ? and course_name = ?", new String[]{selectedClass, selectedCourse}, null, null, null);

                int numcols = cur.getColumnCount(); // 获取总列数
                int numrows = cur.getCount(); // 获取总行数

                Toast.makeText(ExportData.this, "行数: "+numrows+"  列数: "+numcols, Toast.LENGTH_LONG).show();
                String records[][] = new String[numrows+1][numcols]; // 存放从数据表中获取的数据
                int r = 0;

                if (cur.moveToFirst()) {
                    while (cur.getPosition() < numrows) {
                        for (int c = 0; c < numcols; c++) {
                            if (r == 0) {
                                records[r][c] = cur.getColumnName(c);
                                records[r + 1][c] = cur.getString(c);
                            } else {
                                records[r + 1][c] = cur.getString(c);
                            }
                        }
                        cur.moveToNext();
                        r++;
                    }
                    cur.close();
                }
                // 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
                book = Workbook.createWorkbook(childFile);
                // 生成名为"sheet1"的工作表，参数0表示这是第一页
                WritableSheet sheet = book.createSheet("sheet1", 0);

                // 下面开始添加单元格 
                for (int i = 0; i < numrows+1; i++) {
                    for (int j = 0; j < numcols; j++) {
                        // 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行 
                        Label labelC = new Label(j, i, records[i][j]);
                        try {
                            // 将生成的单元格添加到工作表中
                            sheet.addCell(labelC);
                        } catch (RowsExceededException e) {
                            e.printStackTrace();
                        } catch (WriteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (book != null) {
                    try {
                        // 从内存中写入文件中
                        book.write();
                        // 关闭资源，释放内存
                        book.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            Toast.makeText(ExportData.this, "没有SD卡！", Toast.LENGTH_SHORT)
                    .show();
            // Environment.getExternalStorageDirectory():/storage/sdcard0
            System.out.println(Environment.getExternalStorageDirectory()
                    + "0000000000000000000000000000");
        }
    }
}