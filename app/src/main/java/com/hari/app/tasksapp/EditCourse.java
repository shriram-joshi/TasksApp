package com.hari.app.tasksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class EditCourse extends AppCompatActivity {

    EditText name,code;
    Button cancel,done;

    String newName,newCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        name=findViewById(R.id.Name);
        code=findViewById(R.id.Code);
        cancel=findViewById(R.id.buttonCancel);
        done=findViewById(R.id.buttonOk);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newName=name.getText().toString();
                newCode=code.getText().toString();

                Course newCourse=new Course(newName,newCode);

                Realm realm= Realm.getDefaultInstance();

                if(getIntent().getAction().equals("newCourse"))
                {
                    realm.beginTransaction();
                    //push
                    realm.insertOrUpdate(newCourse);
                    realm.commitTransaction();

                    Intent openMain=new Intent(EditCourse.this,MainActivity.class);
                    //These flags are used to prevent showing of previous non-updated recycler view on pressing back button twice, it will clear the stack of activities already running)
                    openMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(openMain);

                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
