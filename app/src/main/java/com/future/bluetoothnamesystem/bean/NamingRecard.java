package com.future.bluetoothnamesystem.bean;

/**
 * Created by baiju on 2015/8/27.
 */
public class NamingRecard {
    public NamingRecard() {
    }

    public NamingRecard(long rec_id, long stu_id, String course, String name, String className,
                        String teacherName, String arrival, String non_arrival, String breaks,
                        String late, String leave, String thisTime) {
        this.rec_id = rec_id;
        this.stu_id = stu_id;
        this.course = course;
        this.name = name;
        this.className = className;
        this.teacherName = teacherName;
        this.arrival = arrival;
        this.non_arrival = non_arrival;
        this.breaks = breaks;
        this.late = late;
        this.leave = leave;
        this.thisTime = thisTime;
    }

    public long getRec_id() {
        return rec_id;
    }

    public void setRec_id(long rec_id) {
        this.rec_id = rec_id;
    }

    public long getStu_id() {
        return stu_id;
    }

    public void setStu_id(long stu_id) {
        this.stu_id = stu_id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getNon_arrival() {
        return non_arrival;
    }

    public void setNon_arrival(String non_arrival) {
        this.non_arrival = non_arrival;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getThisTime() {
        return thisTime;
    }

    public void setThisTime(String thisTime) {
        this.thisTime = thisTime;
    }

    private long rec_id;
    private long stu_id;
    private String course;
    private String name;
    private String className;
    private String teacherName;
    private String arrival;
    private String non_arrival;
    private String breaks;

    public String getBreaks() {
        return breaks;
    }

    public void setBreaks(String breaks) {
        this.breaks = breaks;
    }

    private String late;
    private String leave;
    private String thisTime;

}
