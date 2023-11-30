package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import androidx.core.text.HtmlCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.chattest.MessageActivity;


public class home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ImageView photo = findViewById(R.id.photo);
        ImageView contact = findViewById(R.id.contact);
        ImageView user = findViewById(R.id.user);
        ImageView knowledge = findViewById(R.id.knowledge);
        ImageView method = findViewById(R.id.method);

        TextView tutorial = findViewById(R.id.tutorial);
        TextView popular_science = findViewById(R.id.popular_science);

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.tutorial_popup, null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1600, true);

                Button close = viewPopupwindow.findViewById(R.id.close);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss(); // 关闭弹出窗口
                        }
                    }
                });

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, -100);
            }
        });

        popular_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewPopupwindow = layoutInflater.inflate(R.layout.popular_science_popup, null);

                PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1600, true);

                Button close = viewPopupwindow.findViewById(R.id.close);
                TextView popular_science1 = viewPopupwindow.findViewById(R.id.popular_science_1);
                TextView popular_science2 = viewPopupwindow.findViewById(R.id.popular_science_2);
                popular_science1.setText(HtmlCompat.fromHtml(getString(R.string.popular_science_1), HtmlCompat.FROM_HTML_MODE_LEGACY));
                popular_science2.setText(HtmlCompat.fromHtml(getString(R.string.popular_science_2), HtmlCompat.FROM_HTML_MODE_LEGACY));

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss(); // 关闭弹出窗口
                        }
                    }
                });

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, -100);
            }
        });



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

        method.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(home.this,method.class);//目前Activity與目標Activity
            startActivity(intent);
        });

    }
}