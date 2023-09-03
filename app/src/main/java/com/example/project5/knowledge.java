package com.example.project5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class knowledge extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge);

        ImageView home = findViewById(R.id.home);
        RelativeLayout R1 = findViewById(R.id.R1);


        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(knowledge.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,view.getWidth(),500,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                viewPopupwindow.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });


    }

}
