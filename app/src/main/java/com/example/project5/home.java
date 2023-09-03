package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.chattest.MessageActivity;


public class home extends AppCompatActivity {
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ImageView photo = findViewById(R.id.photo);
        ImageView contact = findViewById(R.id.contact);
        ImageView user = findViewById(R.id.user);
        ImageView knowledge = findViewById(R.id.knowledge);

        photo.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(home.this, photo.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        user.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(home.this, user.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        contact.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(home.this, MessageActivity.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        knowledge.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(home.this, knowledge.class);//目前Activity與目標Activity
            startActivity(intent);
        });

    }
}