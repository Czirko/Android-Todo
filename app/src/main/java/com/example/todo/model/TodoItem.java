package com.example.todo.model;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private int id;
    private String todo;
    private String date;
    private boolean done;
    private boolean express;

    public TodoItem() {
        this.done=false;
        this.id=-1;
    }

    public TodoItem(int id, String todo, boolean express) {
        this.id = id;
        this.todo = todo;
        this.express = express;
        this.done=false;
    }

    public TodoItem(String todo,String date, boolean express) {
        this.todo = todo;
        this.date=date;
        this.express = express;
        this.done=false;
        this.id=-1;
    }

    public TodoItem(int id, String todo, String date, boolean done, boolean express) {
        this.id = id;
        this.todo = todo;
        this.date = date;
        this.done = done;
        this.express = express;
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
