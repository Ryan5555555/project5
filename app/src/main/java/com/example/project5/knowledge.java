package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class knowledge extends AppCompatActivity {

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

                    View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup, null);

                    PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1000, true);

                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                    TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                    TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                    popimg.setImageDrawable(R1img.getDrawable());
                    poptext.setText(R1text.getText());
                    popdescription.setText(Html.fromHtml(getString(R.string.rice_blast)));
                    viewPopupwindow.setOnTouchListener(new View.OnTouchListener() {
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

                    View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup, null);

                    PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1000, true);

                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                    TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                    TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                    popimg.setImageDrawable(R2img.getDrawable());
                    poptext.setText(R2text.getText());

                    popdescription.setText(Html.fromHtml(getString(R.string.bacterial_leaf_blight)));

                    viewPopupwindow.setOnTouchListener(new View.OnTouchListener() {
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

                    View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup, null);

                    PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1000, true);

                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                    TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                    TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                    popimg.setImageDrawable(R3img.getDrawable());
                    poptext.setText(R3text.getText());
                    popdescription.setText(Html.fromHtml(getString(R.string.brown_spot)));
                    viewPopupwindow.setOnTouchListener(new View.OnTouchListener() {
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

                    View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup, null);

                    PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1000, true);

                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                    TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                    TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                    popimg.setImageDrawable(R4img.getDrawable());
                    poptext.setText(R4text.getText());
                    popdescription.setText(Html.fromHtml(getString(R.string.narrow_brown_spot)));

                    viewPopupwindow.setOnTouchListener(new View.OnTouchListener() {
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

                    View viewPopupwindow = layoutInflater.inflate(R.layout.knowledge_popup, null);

                    PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 1000, 1000, true);

                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    ImageView popimg = viewPopupwindow.findViewById(R.id.imageView);
                    TextView poptext = viewPopupwindow.findViewById(R.id.textView5);
                    TextView popdescription = viewPopupwindow.findViewById(R.id.textView6);
                    popimg.setImageDrawable(R5img.getDrawable());
                    poptext.setText(R5text.getText());
                    popdescription.setText(Html.fromHtml(getString(R.string.sheath_blight)));
                    viewPopupwindow.setOnTouchListener(new View.OnTouchListener() {
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
