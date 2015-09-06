package com.future.bluetoothnamesystem.bean;

import java.math.BigInteger;

/**
 * Created by hehehe on 2015/8/27.
 */
public class StudentInfo {

    private String stuId;
    private String stuName;
    private String macAddress;
    private String className;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
