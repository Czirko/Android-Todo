package com.example.todo.DAO;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todo.model.TodoItem;

@Database(entities = {TodoItem.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
    private static TodoDatabase instance;

    public abstract TodoDao todoDao();

    public static synchronized TodoDatabase getInstance(Context context) {

        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class,"todo_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TodoDao todoDao;
        public PopulateDbAsyncTask(TodoDatabase db) {
            todoDao=db.todoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            todoDao.insert(new TodoItem("Semmit","Soha",false,true));
            return null;
        }
    }
}
