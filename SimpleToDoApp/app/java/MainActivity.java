package com.example.simpletodoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TaskAdapter taskAdapter;
    TaskDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTask);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        db = TaskDatabase.getInstance(this);
        List<Task> taskList = db.taskDao().getAll();

        taskAdapter = new TaskAdapter(taskList, db);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }

    public void addTask(View view) {
        String taskText = editText.getText().toString();
        if (!taskText.isEmpty()) {
            Task task = new Task(taskText);
            db.taskDao().insert(task);
            taskAdapter.updateTasks(db.taskDao().getAll());
            editText.setText("");
        } else {
            Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show();
        }
    }
}