package com.example.todo.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.TodoItem;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private List<TodoItem> items;


    public MyRecyclerAdapter(List<TodoItem> items, Context context) {
        this.items = items;

    }

    public MyRecyclerAdapter() {
    }

    public MyRecyclerAdapter(List<TodoItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.recyclerview_item,
               parent,false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TodoItem ti =items.get(position);
        holder.tvTodo.setText(ti.getTodo());
        holder.tvDate.setText(ti.getDate());

        if(ti.isDone()){
            ti.setDate("Done");
        }
        if(ti.isExpress()){
            holder.isExpress.setVisibility(View.VISIBLE);
        }else{
            holder.isExpress.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  void setItems(List<TodoItem> items){
        this.items=items;
        notifyDataSetChanged();
    }

    public TodoItem getNoteAt(int pos){
        return items.get(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public TextView tvTodo;
        public TextView tvDate;
        public ImageView isExpress;
        public LinearLayout rvItemLayout;

        public ViewHolder(@NonNull View iv) {
            super(iv);

            tvTodo=iv.findViewById(R.id.tvTodo);
            tvDate=iv.findViewById(R.id.tvDate);
            isExpress=iv.findViewById(R.id.ivExpress);
            rvItemLayout=iv.findViewById(R.id.rvItemLayout);
            rvItemLayout.setOnCreateContextMenuListener(this );

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(),120,0,"Done" );
            menu.add(this.getAdapterPosition(),121,0,"Edit this item" );
            menu.add(this.getAdapterPosition(),122,0,"Delete this item");
        }


    }
}
