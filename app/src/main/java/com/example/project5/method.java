package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class method extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method);

        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(method.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });
    }
}