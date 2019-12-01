package com.example.recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleview.adapters.ListAdapter;


public class MainFragment extends Fragment {

    OnClickListener mListener;

    public static MainFragment newInstance(OnClickListener listener) {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment(listener);
        fragment.setArguments(args);
        return fragment;
    }

    private MainFragment(OnClickListener listener){
        super(R.layout.fragment_main);
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerView(root);
        return root;
    }

    private void initRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ListAdapter adapter =
                new ListAdapter(getResources().getStringArray(R.array.arr_of_Counter), mListener);
        recyclerView.setAdapter(adapter);
    }
    
}
