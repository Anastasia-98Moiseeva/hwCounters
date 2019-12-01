package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_layout, MainFragment.newInstance(this))
                .commit();
    }

    @Override
    public void onClick(String item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, ItemFragment.newInstance(item))
                .addToBackStack(null)
                .commit();
    }
}

