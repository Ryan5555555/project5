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

import com.example.project5.chattest.MessageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class user extends AppCompatActivity {
    FirebaseAuth auth;

    DatabaseReference userRef;
    FirebaseFirestore db ;
    FirebaseUser user_now;
    ImageView home;
    TextView name, email, phone, address;
    TextInputEditText name_edit, email_edit, phone_edit, address_edit;
    TextInputLayout name_layout, email_layout, phone_layout, address_layout;
    ImageButton name_change, email_change, phone_change, address_change;
    ImageButton name_cancel, email_cancel, phone_cancel, address_cancel;
    Button logout,save;
    @Override
    public void onStart() {
//        test
        super.onStart();
        db = FirebaseFirestore.getInstance();
        user_now=auth.getCurrentUser();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("User").document(user_now.getUid());

        auth = FirebaseAuth.getInstance();

//        email.setText(user_now.getEmail());
//        phone.setText(user_now.getEmail());
//        address.setText(user_now.getEmail());
        if(user_now == null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            Toast.makeText(user.this, "找不到帳號，請聯絡官方人員" , Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else{
//            auth = FirebaseAuth.getInstance();
            user_now=auth.getCurrentUser();
            String userid =user_now.getUid();
            DocumentReference userDocRef = db.collection("User").document(userid);
            userDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // 提取电子邮件地址
                            String userName = document.getString("name");
                            String userEmail = document.getString("email");
                            String userPhone = document.getString("phone");
                            String userAddress = document.getString("address");
                            // 在这里使用 userEmail，例如设置TextView的文本
                            name.setText(userName);
                            email.setText(userEmail);
                            phone.setText(userPhone);
                            address.setText(userAddress);
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
//        user_now=auth.getCurrentUser();
//        String userid =user_now.getUid();
//        userRef = FirebaseDatabase.getInstance().getReference("User").child(userid);


        home = findViewById(R.id.home);
//        textview
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
//        edit
        name_edit = findViewById(R.id.name_edit);
        email_edit = findViewById(R.id.email_edit);
        phone_edit = findViewById(R.id.phone_edit);
        address_edit = findViewById(R.id.address_edit);
//        layout
        name_layout = findViewById(R.id.name_layout);
        email_layout = findViewById(R.id.email_layout);
        phone_layout = findViewById(R.id.phone_layout);
        address_layout = findViewById(R.id.address_layout);
//        change
        name_change = findViewById(R.id.name_change);
        email_change = findViewById(R.id.email_change);
        phone_change = findViewById(R.id.phone_change);
        address_change = findViewById(R.id.address_change);
//        cancel
        name_cancel = findViewById(R.id.name_cancel);
        email_cancel = findViewById(R.id.email_cancel);
        phone_cancel = findViewById(R.id.phone_cancel);
        address_cancel = findViewById(R.id.address_cancel);
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
    }
}