package com.hari.app.tasksapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject {

    private boolean isCheck;
    @PrimaryKey
    private String taskText;
    private String course;

    public Task() {
    }

    public Task(boolean isCheck, String taskText, String course) {
        this.isCheck = isCheck;
        this.taskText = taskText;
        this.course = course;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
