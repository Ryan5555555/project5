package com.example.project5;

import android.Manifest;
import android.annotation.SuppressLint;
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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class knowledge extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge);

        RelativeLayout R1 = findViewById(R.id.R1);
        ImageView R1img = findViewById(R.id.R1image);
        TextView R1text = findViewById(R.id.t1);

        RelativeLayout R2 = findViewById(R.id.R2);
        ImageView R2img = findViewById(R.id.R2image);
        TextView R2text = findViewById(R.id.t2);

        RelativeLayout R3 = findViewById(R.id.R3);
        ImageView R3img = findViewById(R.id.R3image);
        TextView R3text = findViewById(R.id.t3);

        RelativeLayout R4 = findViewById(R.id.R4);
        ImageView R4img = findViewById(R.id.R4image);
        TextView R4text = findViewById(R.id.t4);

        RelativeLayout R5 = findViewById(R.id.R5);
        ImageView R5img = findViewById(R.id.R5image);
        TextView R5text = findViewById(R.id.t5);

        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(knowledge.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });


        //彈出視窗
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                ImageView exit = viewPopupwindow.findViewById(R.id.imageView3);
                popimg.setImageResource(R.drawable.riceblast);
                poptext.setText("我是"+R1text.getText());

                exit.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                ImageView exit = viewPopupwindow.findViewById(R.id.imageView3);
                popimg.setImageDrawable(R2img.getDrawable());
                poptext.setText("我是"+R2text.getText());

                exit.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                ImageView exit = viewPopupwindow.findViewById(R.id.imageView3);
                popimg.setImageDrawable(R3img.getDrawable());
                poptext.setText("我是"+R3text.getText());

                exit.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                ImageView exit = viewPopupwindow.findViewById(R.id.imageView3);
                popimg.setImageDrawable(R4img.getDrawable());
                poptext.setText("我是"+R4text.getText());

                exit.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });
            }
        });

        R5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup,null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow,1000,1000,true);

                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

                ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                ImageView exit = viewPopupwindow.findViewById(R.id.imageView3);
                popimg.setImageDrawable(R5img.getDrawable());
                poptext.setText("我是"+R5text.getText());

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
