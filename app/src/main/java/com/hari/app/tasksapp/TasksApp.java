package com.hari.app.tasksapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TasksApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder().build());
    }
}
