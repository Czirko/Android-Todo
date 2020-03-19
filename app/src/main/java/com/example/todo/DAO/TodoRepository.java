package com.example.todo.DAO;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todo.model.TodoItem;

import java.util.List;

public class TodoRepository {
    private TodoDao todoDao;
    private LiveData<List<TodoItem>> items;

    public TodoRepository(Application app) {
        TodoDatabase db = TodoDatabase.getInstance(app);
        todoDao=db.todoDao();
        items=todoDao.getAllTodo();
    }

    public void insert(TodoItem todo){
        new InsertTodoAsyncTasc(todoDao).execute(todo);
    }

    public void update(TodoItem todo){
        new UpdateTodoAsyncTasc(todoDao).execute(todo);
    }

    public void delete(TodoItem todo){
        new DeleteTodoAsyncTasc(todoDao).execute(todo);
    }
    public void deleteAllTodo(){
        new DeleteAllTodoAsyncTasc(todoDao).execute();
    }
    public LiveData<List<TodoItem>> getAllTodo(){
        return items;
    }


    private class InsertTodoAsyncTasc extends AsyncTask<TodoItem,Void,Void> {
        private TodoDao todoDao;
        public InsertTodoAsyncTasc(TodoDao todoDao) {
            this.todoDao=todoDao;
        }
        @Override
        protected Void doInBackground(TodoItem... todoItems) {
            todoDao.insert(todoItems[0]);
            return null;
        }
    }

    private class UpdateTodoAsyncTasc extends AsyncTask<TodoItem,Void,Void> {
        private TodoDao todoDao;
        public UpdateTodoAsyncTasc(TodoDao todoDao) {
            this.todoDao=todoDao;
        }
        @Override
        protected Void doInBackground(TodoItem... todoItems) {
            todoDao.update(todoItems[0]);
            return null;
        }
    }

    private class DeleteTodoAsyncTasc extends AsyncTask<TodoItem,Void,Void> {
        private TodoDao todoDao;
        public DeleteTodoAsyncTasc(TodoDao todoDao) {
            this.todoDao=todoDao;
        }
        @Override
        protected Void doInBackground(TodoItem... todoItems) {
            todoDao.delete(todoItems[0]);
            return null;
        }
    }

    private class DeleteAllTodoAsyncTasc extends AsyncTask<Void,Void,Void> {
        private TodoDao todoDao;
        public DeleteAllTodoAsyncTasc(TodoDao todoDao) {
            this.todoDao=todoDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAllTodo();
            return null;
        }
    }
}
