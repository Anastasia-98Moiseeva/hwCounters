package com.example.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private final List<String> counters;

    public SpinnerAdapter(@NonNull List<String> counters) {
        this.counters = counters;
    }

    @Override
    public int getCount() {
        return counters.size();
    }

    @Override
    public String getItem(int position) {
        return counters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.counterName.setText(getItem(position));
        return convertView;
    }

    private class ViewHolder{
        private final TextView counterName;

        private ViewHolder(View mLectorName) {
            this.counterName = (TextView) mLectorName;
        }
    }
}
