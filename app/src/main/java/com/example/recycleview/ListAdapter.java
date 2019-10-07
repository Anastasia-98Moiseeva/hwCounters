package com.example.recycleview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemsHolder> {

    private String[] names_of_list_items;

    ListAdapter(String[] names_of_list_items){
        this.names_of_list_items = names_of_list_items;
    }

    @NonNull
    @Override
    public ListItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_list, parent, false);
        Button button = view.findViewById(R.id.item);
        return new ListItemsHolder(view, button);
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

        public ListItemsHolder(@NonNull View itemView, Button item) {
            super(itemView);
            this.item = item;
            item.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v)
                {
                    v.getContext().startActivity(new Intent(v.getContext(), ItemActivity.class));
                }
            });
        }
    }
}
