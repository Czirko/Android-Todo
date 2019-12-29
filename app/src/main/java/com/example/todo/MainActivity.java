package com.example.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.todo.adapter.MyRecyclerAdapter;
import com.example.todo.model.TodoDAO;
import com.example.todo.model.TodoDAOImp;
import com.example.todo.model.TodoItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQC_NEW = 1;
    private static final int REQC_EDIT = 2;


    private List<TodoItem> items;
    private RecyclerView rcView;
    private RecyclerView.Adapter rcAdapter;
    private TodoDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcView=findViewById(R.id.rvTodo);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        dao=new TodoDAOImp(this);
        items=dao.getAllTodo();
        rcAdapter=new MyRecyclerAdapter(items,this);
        rcView.setAdapter(rcAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TodoItemActivity.class);
                startActivityForResult(i, REQC_NEW);
            }
        });



    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int index= item.getGroupId();
       TodoItem ti = items.get(index);
        switch (item.getItemId()){

            case 120:
                ti.setDate("Done");
                ti.setDone(true);
                items.set(index,ti);
                rcAdapter.notifyDataSetChanged();
                dao.saveTodo(ti);
                return true;

            case 121:

                Intent i = new Intent(getApplicationContext(), TodoItemActivity.class);
                i.putExtra("ti", ti);
                i.putExtra("index", item.getGroupId());
                startActivityForResult(i, REQC_EDIT);
                displayMessage("Item for editing "+item.getItemId()+" index: "+item.getGroupId());

                return true;

            case 122:
                displayMessage("Item deleted");
                items.remove(ti);
                rcAdapter.notifyItemRemoved(item.getGroupId());
                return true;

                default:
                    return super.onContextItemSelected(item);

        }

    }
    private void displayMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mi_newItem) {
            Intent i = new Intent(getApplicationContext(),TodoItemActivity.class);
            startActivityForResult(i,REQC_NEW);
            return true;
        }else if(id==R.id.mi_removeAll){
            AlertDialog.Builder b=new AlertDialog.Builder(this);
            b.setTitle("Törlési megerősítés")
                    .setMessage("Are you sure?")
                    .setNegativeButton("No, im Not sure", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Hello Mr. NotSure ", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        }

                    });
            b.setPositiveButton("Yes, im sure", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, " Hello Mr. Sure :)", Toast.LENGTH_SHORT).show();

                    items=new ArrayList<>();
                    rcAdapter=new MyRecyclerAdapter(items,getApplicationContext());
                    rcView.setAdapter(rcAdapter);
                    dao.deleteAllTodo();
                }
            });
            b.create().show();

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            TodoItem ti = (TodoItem) data.getSerializableExtra("ti");


            if (requestCode == REQC_NEW) {
                items.add(ti);
                rcAdapter.notifyDataSetChanged();
                Log.d("LLLLL","Saving: "+ti.getTodo());
                dao.saveTodo(ti);


            } else if (requestCode == REQC_EDIT) {
                int index=  data.getIntExtra("index", -1);
                items.set(index,ti);
                rcAdapter.notifyDataSetChanged();
                dao.saveTodo(ti);
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
