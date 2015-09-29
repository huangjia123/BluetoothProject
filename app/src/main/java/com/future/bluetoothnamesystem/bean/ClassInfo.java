package com.future.bluetoothnamesystem.bean;

/**
 * Created by baiju on 2015/9/29.
 */
public class ClassInfo {
    private String className;
    private int stuCount;

    public void ClassInfo(){

    }

    public ClassInfo(String className, int stuCount) {
        this.className = className;
        this.stuCount = stuCount;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }
}
