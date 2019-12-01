package com.example.recycleview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.OnClickListener;
import com.example.recycleview.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemsHolder> {

    private String[] names_of_list_items;
    OnClickListener listener;

    public ListAdapter(String[] names_of_list_items, OnClickListener listener){
        this.names_of_list_items = names_of_list_items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_list,
                parent, false);
        Button button = view.findViewById(R.id.item);
        return new ListItemsHolder(view, button, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemsHolder holder, int position) {
        holder.item.setText(names_of_list_items[position]);
    }

    @Override
    public int getItemCount() {
        return names_of_list_items.length;
    }

    static class ListItemsHolder extends RecyclerView.ViewHolder{

        private Button item;
        private OnClickListener listener;

        public ListItemsHolder(@NonNull View itemView, final Button item,
                               final OnClickListener listener) {
            super(itemView);
            this.item = item;
            this.listener = listener;

            item.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v)
                {
                    listener.onClick((String) item.getText());
                }
            });
        }
    }
}
