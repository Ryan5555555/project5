package com.example.project5;


import android.content.Intent;
import android.os.Bundle;
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
        if ("正常葉片".equals(result_name)) {
            result.setText("正常無病害");
        } else if ("稻熱病".equals(result_name)) {
            result.setText("稻熱病");
            confidence.setText(result_confidence+"%");
            img.setImageResource(R.drawable.rice);
            describe.setText(getString(R.string.rice_blast)); // 使用getString()方法來設置 TextView 的文字
        } else if ("胡麻葉枯病".equals(result_name)) {
            result.setText("胡麻葉枯病");
            confidence.setText(result_confidence+"%");
            img.setImageResource(R.drawable.brownspot);
            describe.setText(getString(R.string.brown_spot));
        } else if ("紋枯病".equals(result_name)) {
            result.setText("紋枯病");
            confidence.setText(result_confidence+"%");
            img.setImageResource(R.drawable.sheathblight);
            describe.setText(getString(R.string.sheath_blight));
        } else if ("白葉枯病".equals(result_name)) {
            result.setText("白葉枯病");
            confidence.setText(result_confidence+"%");
            img.setImageResource(R.drawable.bacterialleafblight);
            describe.setText(getString(R.string.bacterial_leaf_blight));
        } else if ("褐條葉枯病".equals(result_name)) {
            result.setText("褐條葉枯病");
            confidence.setText(result_confidence+"%");
            img.setImageResource(R.drawable.narrowbrownleafspot);
            describe.setText(getString(R.string.narrow_brown_spot));
        }
        else
        {Toast.makeText(getApplicationContext(), "辨識跳轉出錯，請填系工作團隊", Toast.LENGTH_SHORT).show();}
    }
}
