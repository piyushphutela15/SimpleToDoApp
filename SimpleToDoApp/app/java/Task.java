package com.example.simpletodoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public Task(String name) {
        this.name = name;
    }
}