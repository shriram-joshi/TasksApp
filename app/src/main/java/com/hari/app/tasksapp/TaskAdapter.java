package com.hari.app.tasksapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskRvVH> {

    ArrayList<Task> taskList;
    Context context;
    Realm realm;
    String courseCode;

    public TaskAdapter(ArrayList<Task> taskList, Context context, String courseCode) {
        this.taskList = taskList;
        this.context = context;
        this.courseCode = courseCode;
        realm=Realm.getDefaultInstance();
        loadTasks();
    }

    private void loadTasks(){
        RealmQuery<Task> query=realm.where(Task.class);
        RealmResults<Task> results=query.equalTo("course",courseCode).findAll();
        taskList.addAll(results);
        notifyDataSetChanged();
    }

    void addTask(Task task){
        taskList.add(task);
        realm.beginTransaction();
        realm.insertOrUpdate(task);
        realm.commitTransaction();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskRvVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskRvVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasks, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRvVH holder, int position) {
        holder.populate(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskRvVH extends RecyclerView.ViewHolder{

        private CheckBox check;
        private TextView taskText;
        public TaskRvVH(@NonNull View itemView) {
            super(itemView);
            check=itemView.findViewById(R.id.item_check);
            taskText=itemView.findViewById(R.id.item_text);
        }

        void populate(final Task task){
            check.setChecked(task.isCheck());
            taskText.setText(task.getTaskText());
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    realm.beginTransaction();
                    task.setCheck(check.isChecked());
                    realm.insertOrUpdate(task);
                    realm.commitTransaction();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
