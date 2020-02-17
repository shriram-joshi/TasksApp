package com.hari.app.tasksapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {

    ArrayList<Course> courseList;
    Context context;

    Realm realm;

    public CourseAdapter(ArrayList<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;

        realm=Realm.getDefaultInstance();

        loadCourses();
    }

    void loadCourses()
    {
        RealmQuery<Course> realmQuery=realm.where(Course.class);
        RealmResults<Course> realmResults=realmQuery.findAll();

        courseList.addAll(realmResults);

        //This method will notify the app of changes in the ArrayList and force an update
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseVH holder, int position) {
        holder.populateCourse(courseList.get(position));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    class CourseVH extends RecyclerView.ViewHolder{

        Button courseBtn;

        public CourseVH(@NonNull View itemView) {
            super(itemView);
            courseBtn=itemView.findViewById(R.id.courseBtn);
        }

        void populateCourse(final Course course){
            courseBtn.setText(course.getCourseName()+" "+course.getCourseCode());
            courseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent loadTasks = new Intent(context,TasksActivity.class);
                    loadTasks.putExtra("courseCode",course.getCourseCode());
                    itemView.getContext().startActivity(loadTasks);
                }
            });
            courseBtn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new MaterialAlertDialogBuilder(context).setTitle("Warning").setMessage("Are you sure you want to delete this course?").setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Realm realm=Realm.getDefaultInstance();
                            String courseCode=course.getCourseCode();
                            RealmResults<Course> results=realm.where(Course.class).equalTo("courseCode",courseCode).findAll();
                            courseList.removeAll(results);
                            realm.beginTransaction();
                            results.deleteAllFromRealm();
                            realm.commitTransaction();
                            notifyDataSetChanged();
                            Toast.makeText(context,"User clicked delete",Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"User clicked cancel",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                    return true;
                }
            });
        }
    }
}
