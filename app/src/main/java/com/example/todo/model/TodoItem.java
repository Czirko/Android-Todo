package com.example.todo.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "todo_table")
public class TodoItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String todo;

    private String date;

    private boolean done;

    private boolean express;


    public TodoItem(String todo, String date, boolean done, boolean express) {
        this.todo = todo;
        this.date = date;
        this.done = done;
        this.express = express;
    }
    @Ignore
    public TodoItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isExpress() {
        return express;
    }

    public void setExpress(boolean express) {
        this.express = express;
    }


}
