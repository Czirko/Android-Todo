package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

import com.example.todo.model.TodoItem;

import java.util.Calendar;

public class TodoItemActivity extends AppCompatActivity {

    private EditText etTodo;
    private Button btnDate;
    private Switch switchExpress;
    private TodoItem ti;
    private Intent intent;
    private Calendar cal;
    private DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_item);

        etTodo = findViewById(R.id.etTodo);
        btnDate = findViewById(R.id.btnDate);
        switchExpress = findViewById(R.id.switchExpress);

        cal = Calendar.getInstance();
        final int day = cal.get(Calendar.DAY_OF_MONTH);
        final int month = cal.get(Calendar.MONTH);
        final int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Todo");


        intent = getIntent();
        ti = (TodoItem) intent.getSerializableExtra("ti");

        if (ti != null) {
            etTodo.setText(ti.getTodo());
            btnDate.setText(ti.getDate());
            switchExpress.setChecked(ti.isExpress());
        } else {
            btnDate.setText(year + "." + month + "." + day);
        }

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd = new DatePickerDialog(TodoItemActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int myear, int mMonth, int mDay) {
                        btnDate.setText(myear + "." + mMonth + "." + mDay);
                    }
                }, year, month, day);
                dpd.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_todo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.save_todo:
                saveTodo();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void saveTodo() {
        if (ti == null) {
            ti = new TodoItem();
        }
        ti.setTodo(etTodo.getText().toString());
        ti.setDate(btnDate.getText().toString());
        ti.setExpress(switchExpress.isChecked());

        intent.putExtra("ti", ti);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
