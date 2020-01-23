package com.hari.app.tasksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseVH> {

    ArrayList<Course> courseList;
    Context context;

    public CourseAdapter(ArrayList<Course> coursList, Context context) {
        this.courseList = coursList;
        this.context = context;
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

        void populateCourse(Course course){
            courseBtn.setText(course.getCourseName()+" "+course.getCourseCode());
        }
    }
}
