package com.hari.app.tasksapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Course extends RealmObject {
    public String courseName;
    @PrimaryKey
    private String courseCode;

    public Course() {
        courseName="";
        courseCode="";
    }

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
