package com.example.todo.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="todolist.db" ;
    private static final int DATABASE_VERSION =1 ;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todoitem(_id INTEGER PRIMARY KEY AUTOINCREMENT," +"todo TEXT," + " date TEXT," + " express INTEGER," + " done INTEGER)");
        ContentValues cv = new ContentValues();
        cv.put("todo","valami");
        cv.put("date","2019.07.05");
        cv.put("express","1");

        db.insert("todoitem",null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE todoitem");
        onCreate(db);
    }
}
