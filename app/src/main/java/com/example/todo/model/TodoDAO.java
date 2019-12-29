package com.example.todo.model;

import java.util.List;

public interface TodoDAO {

    public List<TodoItem> getAllTodo();
    public void deleteTodo(TodoItem ti);
    public void saveTodo(TodoItem ti);
    public void deleteAllTodo();


}
