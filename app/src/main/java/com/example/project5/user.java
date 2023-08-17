package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class user extends AppCompatActivity {
    TextView test;
    FirebaseAuth auth;
    FirebaseUser usernow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        auth = FirebaseAuth.getInstance();
        usernow = auth.getCurrentUser();
        test = findViewById(R.id.user);
        Button logout =findViewById(R.id.logout);

        if (usernow == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            test.setText(usernow.getEmail());
        }
        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(user.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}