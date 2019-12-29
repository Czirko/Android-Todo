package com.example.todo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TodoDAOImp implements TodoDAO {

    private DbHelper helper;
    private SQLiteDatabase db;

    public TodoDAOImp(Context context) {
        this.helper = new DbHelper(context);
    }

    @Override
    public List<TodoItem> getAllTodo() {
        db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM todoitem", null);
        c.moveToFirst();
        List<TodoItem> items = new ArrayList<>();
        while (!c.isAfterLast()) {
            int id = c.getInt(c.getColumnIndex("_id"));

            String todo = c.getString(c.getColumnIndex("todo"));
            String date = c.getString(c.getColumnIndex("date"));
            Boolean express = c.getInt(c.getColumnIndex("express"))==1;
            Boolean done=c.getInt(c.getColumnIndex("done"))==1;


            TodoItem ti = new TodoItem(id, todo, date, express, done);
            items.add(ti);
            c.moveToNext();
        }



        c.close();
        db.close();
        return items;


    }

    @Override
    public void deleteTodo(TodoItem ti) {
        db = helper.getWritableDatabase();
        db.delete("todoitem", "_id=?", new String[]{ti.getId() + ""});
        db.close();

    }

    @Override
    public void saveTodo(TodoItem ti) {
        db=helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("todo",ti.getTodo());
        cv.put("date",ti.getDate());
        cv.put("express",booleanToInteger(ti.isExpress()));
        cv.put("done",booleanToInteger(ti.isDone()));
        if(ti.getId()==-1){
            long id =db.insert("todoitem",null,cv);
            ti.setId((int)id);
        }else{
            db.update("todoitem",cv,"_id=?",new String[]{ti.getId()+""});
        }

        db.close();

    }
    private int booleanToInteger(boolean b){
        if(b){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void deleteAllTodo() {
        db=helper.getWritableDatabase();
        db.execSQL("DELETE FROM todoitem");
        db.close();
    }
}
