package com.example.project5;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class result extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView result = findViewById(R.id.result);
        TextView confidence = findViewById(R.id.confidence);
        TextView describe = findViewById(R.id.describe);
        ImageView img =findViewById(R.id.img);

        SharedPreferences sharedPreferences = getSharedPreferences("method", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Boolean check = true;
        Button method = findViewById(R.id.method);
        method.setEnabled(true);


        ImageView home = findViewById(R.id.home);
        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(result.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        Intent intent = getIntent();
        String result_name = intent.getStringExtra("result");
        String result_confidence = intent.getStringExtra("result_confidence");

        // 根据传递的result
        if ("".equals(result_name)) {
            result.setText("健康葉片");
            confidence.setText("");
            describe.setText("病害辨識為健康葉片");
        } else if ("稻熱病".equals(result_name)) {
            result.setText("稻熱病");
            confidence.setText("信心度:"+result_confidence+"%");
            img.setImageResource(R.drawable.rice);
            describe.setText(Html.fromHtml(getString(R.string.rice_blast))); // 使用getString()方法來設置 TextView 的文字
        } else if ("胡麻葉枯病".equals(result_name)) {
            result.setText("胡麻葉枯病");
            confidence.setText("信心度:"+result_confidence+"%");
            img.setImageResource(R.drawable.brownspot);
            describe.setText(Html.fromHtml(getString(R.string.brown_spot)));
        } else if ("紋枯病".equals(result_name)) {
            result.setText("紋枯病");
            confidence.setText("信心度:"+result_confidence+"%");
            img.setImageResource(R.drawable.sheathblight);
            describe.setText(Html.fromHtml(getString(R.string.sheath_blight)));
        } else if ("白葉枯病".equals(result_name)) {
            result.setText("白葉枯病");
            confidence.setText("信心度:"+result_confidence+"%");
            img.setImageResource(R.drawable.bacterialleafblight);
            describe.setText(Html.fromHtml(getString(R.string.bacterial_leaf_blight)));
        } else if ("褐條葉枯病".equals(result_name)) {
            result.setText("褐條葉枯病");
            confidence.setText("信心度:"+result_confidence+"%");
            img.setImageResource(R.drawable.narrowbrownleafspot);
            describe.setText(Html.fromHtml(getString(R.string.narrow_brown_spot)));
        }
        else
        {Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show(); check = false;}

        Boolean finalCheck = check;
        method.setOnClickListener(v -> {
            if (finalCheck){
                int methodnum = sharedPreferences.getInt("method", 0);
                if(methodnum == 5){Toast.makeText(getApplicationContext(), "收藏已滿(收藏上限為五)!", Toast.LENGTH_SHORT).show();}
                else{
                    global.method+=1;

                    editor.putInt("method",global.method);
                    editor.putString(Integer.toString(global.method), result_name);
                    // 提交更改
                    editor.apply();

                    Intent intent_M = new Intent();
                    intent_M.setClass(result.this, method.class);
                    startActivity(intent_M);

                    method.setEnabled(false);
                }
            }
            else{Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show();}
        });
    }
}
