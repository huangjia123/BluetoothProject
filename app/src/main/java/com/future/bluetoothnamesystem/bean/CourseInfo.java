package com.future.bluetoothnamesystem.bean;

/**
 * Created by baiju on 2015/8/25.
 * 选择的课程实体
 */
public class CourseInfo {
    /**选择状态*/
    private boolean isChecked;
    /**课程名*/
    private String itemName;

    public CourseInfo() {

    }

    public CourseInfo(boolean isChecked, String itemName) {
        this.isChecked = isChecked;
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
