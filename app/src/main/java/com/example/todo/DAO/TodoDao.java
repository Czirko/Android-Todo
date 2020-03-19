package com.example.todo.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.model.TodoItem;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insert (TodoItem todo);

    @Update
    void update(TodoItem todo);

    @Delete
    void delete(TodoItem todo);

    @Query("DELETE FROM todo_table")
    void deleteAllTodo();

    @Query("Select * from todo_table order by date desc")
    LiveData<List<TodoItem>> getAllTodo();

}
