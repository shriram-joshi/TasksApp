package com.hari.app.tasksapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {

    RecyclerView taskRV;
    TaskAdapter adapter;
    ArrayList<Task> taskList = new ArrayList<>();
    Button addButton;
    EditText addTaskET;
    String courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        attachID();

        courseCode=getIntent().getStringExtra("courseCode");

        taskRV.setLayoutManager(new LinearLayoutManager(this));
        adapter=new TaskAdapter(taskList,this,courseCode);
        taskRV.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addTask(new Task(false,addTaskET.getText().toString(), courseCode));
                addTaskET.setText("");
            }
        });


    }

    private void attachID(){
        taskRV=findViewById(R.id.tasksRV);
        addButton=findViewById(R.id.addbtn);
        addTaskET=findViewById(R.id.taskEt);
    }
}
