package com.example.project5;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import com.example.project5.chattest.MessageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class register extends AppCompatActivity {
    TextInputEditText editTextName,editTextEmail,editTextPassword,editTextPhone,editTextAddress;
    FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),user.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPhone = findViewById(R.id.phone);
        editTextAddress = findViewById(R.id.address);

        mAuth = FirebaseAuth.getInstance();

        Button register = findViewById(R.id.register);
        Button back_to_login = findViewById(R.id.back_to_login);

        back_to_login.setOnClickListener(v -> {
            back_to_login.setEnabled(false);
            Intent intent = new Intent();
            intent.setClass(register.this, MainActivity.class);//目前Activity與目標Activity
            startActivity(intent);
            v.postDelayed(new Runnable() {
                @Override
                public void run() {
                    back_to_login.setEnabled(true);
                }
            }, 100); // 100毫秒，可以根据需要调整延迟的时间
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.setEnabled(false);
                String name,email, password,phone,address;
                name = String.valueOf(editTextName.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                phone = String.valueOf(editTextPhone.getText());
                address = String.valueOf(editTextAddress.getText());

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(register.this, "您尚未輸入姓名，請重新輸入", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                    return;
                }
                else if (TextUtils.isEmpty(email)) {
                     Toast.makeText(register.this, "您尚未輸入電子郵件，請重新輸入", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                     return;
                 }
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(register.this, "您尚未輸入密碼，請重新輸入", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                    return;
                }
                else if (password.length()<6) {
                     Toast.makeText(register.this, "密碼數字需至少輸入六位數", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                     return;
                 }
                else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(register.this, "您尚未輸入行動電話號碼，請重新輸入", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                    return;
                }
                else if (phone.matches("\\d{10}") == false) {
                    Toast.makeText(register.this, "行動電話號碼格式不正確，請重新輸入", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                    return;
                }
                else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(register.this, "您尚未輸入地址，請重新輸入", Toast.LENGTH_SHORT).show();
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            register.setEnabled(true);
                        }
                    }, 100); // 100毫秒，可以根据需要调整延迟的时间
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    assert firebaseUser != null;
                                    String userid = firebaseUser.getUid();

                                    HashMap<String, Object> User = new HashMap<>();
                                    User.put("id", userid);
                                    User.put("name", name);
                                    User.put("email", email);
                                    User.put("phone", phone);
                                    User.put("address", address);
                                    User.put("timestamp", FieldValue.serverTimestamp());

                                    // 将数据存储到 Firestore
                                    db.collection("User")
                                            .document(userid)
                                            .set(User)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // 在此处执行您想要的操作，例如跳转到用户界面
                                                        Intent intent = new Intent(getApplicationContext(), user.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(register.this, "創建帳號失敗: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                            });

                                } else {
                                    // 如果注册失败，向用户显示消息
                                    Toast.makeText(register.this, "創建帳號失敗.", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(register.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
             }
        });
    }
}