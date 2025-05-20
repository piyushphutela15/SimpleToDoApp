package com.example.simpletodoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    List<Task> taskList;
    TaskDatabase db;

    public TaskAdapter(List<Task> taskList, TaskDatabase db) {
        this.taskList = taskList;
        this.db = db;
    }

    public void updateTasks(List<Task> newTasks) {
        taskList = newTasks;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;
        ImageButton deleteBtn;

        public ViewHolder(View view) {
            super(view);
            taskText = view.findViewById(R.id.taskText);
            deleteBtn = view.findViewById(R.id.deleteBtn);
        }
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskText.setText(task.name);
        holder.deleteBtn.setOnClickListener(v -> {
            db.taskDao().delete(task);
            updateTasks(db.taskDao().getAll());
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}