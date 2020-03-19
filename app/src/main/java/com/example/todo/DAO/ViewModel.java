package com.example.todo.DAO;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todo.model.TodoItem;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private TodoRepository repository;
    private LiveData<List<TodoItem>> items;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new TodoRepository(application);
        items=repository.getAllTodo();
    }

    public void insert(TodoItem todo){
        repository.insert(todo);
    }

    public void update(TodoItem todo){
        repository.update(todo);
    }

    public void delete(TodoItem todo){
        repository.delete(todo);
    }

    public void deleteAllTodo(){
        repository.deleteAllTodo();
    }

    public LiveData<List<TodoItem>> getAllNotes(){
        return items;
    }

}
