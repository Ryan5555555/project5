package com.example.project5;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class method_enter extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_enter);
        TextView result = findViewById(R.id.result);
        TextView describe = findViewById(R.id.describe);
        ImageView img =findViewById(R.id.img);


        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(method_enter.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        Intent intent = getIntent();
        String result_name = intent.getStringExtra("Name");

        if ("正常葉片".equals(result_name)) {
            result.setText("正常無病害");
            describe.setText("");
        } else if ("稻熱病".equals(result_name)) {
            result.setText("稻熱病");
            img.setImageResource(R.drawable.rice);
            describe.setText(Html.fromHtml(getString(R.string.rice_blast))); // 使用getString()方法來設置 TextView 的文字
        } else if ("胡麻葉枯病".equals(result_name)) {
            result.setText("胡麻葉枯病");
            img.setImageResource(R.drawable.brownspot);
            describe.setText(Html.fromHtml(getString(R.string.brown_spot)));
        } else if ("紋枯病".equals(result_name)) {
            result.setText("紋枯病");
            img.setImageResource(R.drawable.sheathblight);
            describe.setText(Html.fromHtml(getString(R.string.sheath_blight)));
        } else if ("白葉枯病".equals(result_name)) {
            result.setText("白葉枯病");
            img.setImageResource(R.drawable.bacterialleafblight);
            describe.setText(Html.fromHtml(getString(R.string.bacterial_leaf_blight)));
        } else if ("褐條葉枯病".equals(result_name)) {
            result.setText("褐條葉枯病");
            img.setImageResource(R.drawable.narrowbrownleafspot);
            describe.setText(Html.fromHtml(getString(R.string.narrow_brown_spot)));
        }
        else
        {Toast.makeText(getApplicationContext(), "跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show();}

    }
}
