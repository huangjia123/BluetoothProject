package com.future.bluetoothnamesystem.bean;

/**
 * Created by Administrator on 2016/2/25.
 */
public class UserRecord {
    String stu_id;
    String course_name;
    String class_name;
    String stu_name;
    String teacher_name;
    int arrival;
    int non_arrival;
    int late;
    int breaks;
    int this_time;
    public int getThis_time() {
        return this_time;
    }

    public void setThis_time(int this_time) {
        this.this_time = this_time;
    }

    public int getBreaks() {
        return breaks;
    }

    public void setBreaks(int breaks) {
        this.breaks = breaks;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getNon_arrival() {
        return non_arrival;
    }

    public void setNon_arrival(int non_arrival) {
        this.non_arrival = non_arrival;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }



    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }
}
