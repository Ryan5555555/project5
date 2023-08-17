package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


public class contact extends AppCompatActivity {
    DatabaseReference reference;
    Intent intent;
    FirebaseUser fuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        ImageView home = findViewById(R.id.home);


        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(contact.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });
    }
}

