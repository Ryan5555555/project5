package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;



import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CompoundButton;
import android.widget.Switch;

public class user extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore db ;
    FirebaseUser user_now;
    ImageView home;
    TextView name, email, phone, address,period,variety;
    TextInputEditText name_edit, email_edit, phone_edit, address_edit, period_edit, variety_edit;
    TextInputLayout name_layout, email_layout, phone_layout, address_layout,period_layout,variety_layout;
    ImageButton name_change, email_change, phone_change, address_change,period_change,variety_change;
    ImageButton name_cancel, email_cancel, phone_cancel, address_cancel,period_cancel,variety_cancel;
    Button logout,save;

    private Switch switch1;

    @Override
    public void onStart() {
//        test
        super.onStart();
        db = FirebaseFirestore.getInstance();
        user_now=auth.getCurrentUser();
        auth = FirebaseAuth.getInstance();

        if(user_now == null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            Toast.makeText(user.this, "找不到帳號，請聯絡官方人員" , Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else{
            user_now=auth.getCurrentUser();
            String userid =user_now.getUid();
            DocumentReference userDocRef = db.collection("User").document(userid);
            userDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            String userName = document.getString("name");
                            String userEmail = document.getString("email");
                            String userPhone = document.getString("phone");
                            String userAddress = document.getString("address");
                            String userPeriod = document.getString("period");
                            String userVariety = document.getString("variety");

                            if (document.getString("name")==null)
                            {name.setText("尚未輸入姓名");}
                            else {name.setText(userName);}

                            if (document.getString("email")==null)
                            {email.setText("尚未輸入電子郵件");}
                            else {email.setText(userEmail);}

                            if (document.getString("phone")==null)
                            {phone.setText("尚未輸入行動電話號碼");}
                            else {phone.setText(userPhone);}

                            if (document.getString("address")==null)
                            {address.setText("尚未輸入作物地址");}
                            else {address.setText(userAddress);}

                            if (document.getString("period")==null)
                            {period.setText("尚未輸入作物種植時期");}
                            else {period.setText(userPeriod);}

                            if (document.getString("variety")==null)
                            {variety.setText("尚未輸入作物品種");}
                            else {variety.setText(userVariety);}


                        } else {
                            Toast.makeText(user.this, "文檔不存在，請聯絡工作人員", Toast.LENGTH_SHORT).show();  // 处理文档不存在的情况
                        }
                    } else {
                        Toast.makeText(user.this, "處理檔案失敗，請聯絡工作人員", Toast.LENGTH_SHORT).show(); // 处理任务失败的情况
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        auth = FirebaseAuth.getInstance();

        home = findViewById(R.id.home);
//        textview
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        period = findViewById(R.id.period);
        variety = findViewById(R.id.variety);
//        edit
        name_edit = findViewById(R.id.name_edit);
        email_edit = findViewById(R.id.email_edit);
        phone_edit = findViewById(R.id.phone_edit);
        address_edit = findViewById(R.id.address_edit);
        period_edit = findViewById(R.id.period_edit);
        variety_edit = findViewById(R.id.variety_edit);
//        layout
        name_layout = findViewById(R.id.name_layout);
        email_layout = findViewById(R.id.email_layout);
        phone_layout = findViewById(R.id.phone_layout);
        address_layout = findViewById(R.id.address_layout);
        period_layout = findViewById(R.id.period_layout);
        variety_layout = findViewById(R.id.variety_layout);
//        change
        name_change = findViewById(R.id.name_change);
        email_change = findViewById(R.id.email_change);
        phone_change = findViewById(R.id.phone_change);
        address_change = findViewById(R.id.address_change);
        period_change = findViewById(R.id.period_change);
        variety_change = findViewById(R.id.variety_change);
//        cancel
        name_cancel = findViewById(R.id.name_cancel);
        email_cancel = findViewById(R.id.email_cancel);
        phone_cancel = findViewById(R.id.phone_cancel);
        address_cancel = findViewById(R.id.address_cancel);
        period_cancel = findViewById(R.id.period_cancel);
        variety_cancel = findViewById(R.id.variety_cancel);

//        switch
        switch1 = findViewById(R.id.switch1);
        SharedPreferences switchStatePreferences;
        switchStatePreferences = getSharedPreferences("SwitchStatePreferences", Context.MODE_PRIVATE);
        switch1.setChecked(switchStatePreferences.getBoolean("switch_state", false));

        // 获取全局变量实例
        GlobalVariable globalVariable = (GlobalVariable) getApplication();
        globalVariable.loadSwitchState(this);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String planet_method = isChecked ? "有機" : "慣行";

                // 更新全局变量
                globalVariable.planet_method = planet_method;

                // 将Switch的状态保存到SharedPreferences中
                SharedPreferences.Editor editor = switchStatePreferences.edit();
                editor.putBoolean("switch_state", isChecked);
                editor.apply();

                Toast.makeText(user.this, "Switch State123: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        //String planet_method = isChecked ? "有機" : "慣行";
        //Intent intent = new Intent();
        //intent.setClass(user.this, knowledge.class);
        //intent.putExtra("planet_method",planet_method);
        //globalVariable.planet_method = planet_method;

//        button
        logout =findViewById(R.id.logout);
        save =findViewById(R.id.save);


        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(user.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {

            logout.setEnabled(false);
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

            // 在点击后设置按钮为灰色并延迟一段时间后恢复
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    logout.setEnabled(true);
                }
            }, 100); // 1000毫秒 = 1秒，可以根据需要调整延迟的时间
        });

        save.setOnClickListener(v -> {

            save.setEnabled(false);

            String new_name = name_edit.getText().toString();
            String new_email = email_edit.getText().toString();
            String new_phone = phone_edit.getText().toString();
            String new_address = address_edit.getText().toString();
            String new_period = period_edit.getText().toString();
            String new_variety = variety_edit.getText().toString();

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DocumentReference userDocRef = db.collection("User").document(userId);

            if(!name_edit.getText().toString().isEmpty()) {
                name.setText(new_name);
                name.setVisibility(View.VISIBLE);
                name_change.setVisibility(View.VISIBLE);
                name_layout.setVisibility(View.INVISIBLE);
                name_cancel.setVisibility(View.INVISIBLE);

                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("name", new_name);
                userDocRef.update(updatedData);
            }

            if(!email_edit.getText().toString().isEmpty()) {
                email.setText(new_email);
                email.setVisibility(View.VISIBLE);
                email_change.setVisibility(View.VISIBLE);
                email_layout.setVisibility(View.INVISIBLE);
                email_cancel.setVisibility(View.INVISIBLE);
                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("email", new_email);
                userDocRef.update(updatedData);
            }

            if(!phone_edit.getText().toString().isEmpty()) {
                if (new_phone.matches("\\d{10}")) {
                    phone.setText(new_phone);
                    phone.setVisibility(View.VISIBLE);
                    phone_change.setVisibility(View.VISIBLE);
                    phone_layout.setVisibility(View.INVISIBLE);
                    phone_cancel.setVisibility(View.INVISIBLE);
                    Map<String, Object> updatedData = new HashMap<>();
                    updatedData.put("phone", new_phone);
                    userDocRef.update(updatedData);
                } else {
                    // 电话号码格式无效，显示错误消息
                    Toast.makeText(user.this, "请输入有效的行動電話號碼格式", Toast.LENGTH_SHORT).show();
                }


            }

            if(!address_edit.getText().toString().isEmpty()) {
                address.setText(new_address);
                address.setVisibility(View.VISIBLE);
                address_change.setVisibility(View.VISIBLE);
                address_layout.setVisibility(View.INVISIBLE);
                address_cancel.setVisibility(View.INVISIBLE);
                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("address", new_address);
                userDocRef.update(updatedData);
            }

            if(!period_edit.getText().toString().isEmpty()) {
                period.setText(new_email);
                period.setVisibility(View.VISIBLE);
                period_change.setVisibility(View.VISIBLE);
                period_layout.setVisibility(View.INVISIBLE);
                period_cancel.setVisibility(View.INVISIBLE);
                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("period", new_period);
                userDocRef.update(updatedData);
            }

            if(!variety_edit.getText().toString().isEmpty()) {
                variety.setText(new_email);
                variety.setVisibility(View.VISIBLE);
                variety_change.setVisibility(View.VISIBLE);
                variety_layout.setVisibility(View.INVISIBLE);
                variety_cancel.setVisibility(View.INVISIBLE);
                Map<String, Object> updatedData = new HashMap<>();
                updatedData.put("variety", new_variety);
                userDocRef.update(updatedData);
            }

            // 在点击后设置按钮为灰色并延迟一段时间后恢复
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    save.setEnabled(true);
                }
            }, 100); // 1000毫秒 = 1秒，可以根据需要调整延迟的时间

        });

        name_change.setOnClickListener(v -> {
            name.setVisibility(View.INVISIBLE);
            name_layout.setVisibility(View.VISIBLE);
            name_change.setVisibility(View.INVISIBLE);
            name_cancel.setVisibility(View.VISIBLE);
        });

        email_change.setOnClickListener(v -> {
            email.setVisibility(View.INVISIBLE);
            email_layout.setVisibility(View.VISIBLE);
            email_change.setVisibility(View.INVISIBLE);
            email_cancel.setVisibility(View.VISIBLE);
        });

        phone_change.setOnClickListener(v -> {
            phone.setVisibility(View.INVISIBLE);
            phone_layout.setVisibility(View.VISIBLE);
            phone_change.setVisibility(View.INVISIBLE);
            phone_cancel.setVisibility(View.VISIBLE);
        });

        address_change.setOnClickListener(v -> {
            address.setVisibility(View.INVISIBLE);
            address_layout.setVisibility(View.VISIBLE);
            address_change.setVisibility(View.INVISIBLE);
            address_cancel.setVisibility(View.VISIBLE);
        });

        period_change.setOnClickListener(v -> {
            period.setVisibility(View.INVISIBLE);
            period_layout.setVisibility(View.VISIBLE);
            period_change.setVisibility(View.INVISIBLE);
            period_cancel.setVisibility(View.VISIBLE);
        });

        variety_change.setOnClickListener(v -> {
            variety.setVisibility(View.INVISIBLE);
            variety_layout.setVisibility(View.VISIBLE);
            variety_change.setVisibility(View.INVISIBLE);
            variety_cancel.setVisibility(View.VISIBLE);
        });
//cancel
        name_cancel.setOnClickListener(v -> {
            name_edit.setText("");
            name.setVisibility(View.VISIBLE);
            name_layout.setVisibility(View.INVISIBLE);
            name_change.setVisibility(View.VISIBLE);
            name_cancel.setVisibility(View.INVISIBLE);
        });

        email_cancel.setOnClickListener(v -> {
            email_edit.setText("");
            email.setVisibility(View.VISIBLE);
            email_layout.setVisibility(View.INVISIBLE);
            email_change.setVisibility(View.VISIBLE);
            email_cancel.setVisibility(View.INVISIBLE);
        });

        phone_cancel.setOnClickListener(v -> {
            phone_edit.setText("");
            phone.setVisibility(View.VISIBLE);
            phone_layout.setVisibility(View.INVISIBLE);
            phone_change.setVisibility(View.VISIBLE);
            phone_cancel.setVisibility(View.INVISIBLE);
        });

        address_cancel.setOnClickListener(v -> {
            address_edit.setText("");
            address.setVisibility(View.VISIBLE);
            address_layout.setVisibility(View.INVISIBLE);
            address_change.setVisibility(View.VISIBLE);
            address_cancel.setVisibility(View.INVISIBLE);
        });

        period_cancel.setOnClickListener(v -> {
            period_edit.setText("");
            period.setVisibility(View.VISIBLE);
            period_layout.setVisibility(View.INVISIBLE);
            period_change.setVisibility(View.VISIBLE);
            period_cancel.setVisibility(View.INVISIBLE);
        });

        variety_cancel.setOnClickListener(v -> {
            variety_edit.setText("");
            variety.setVisibility(View.VISIBLE);
            variety_layout.setVisibility(View.INVISIBLE);
            variety_change.setVisibility(View.VISIBLE);
            variety_cancel.setVisibility(View.INVISIBLE);
        });
    }
}