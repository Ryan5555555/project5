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
    TextView textview,test;
    TextInputEditText editTextName,editTextEmail,editTextPassword,editTextPhone,editTextAddress;
    FirebaseAuth mAuth;
    DatabaseReference reference;
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

        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email, password,phone,address;
                name = String.valueOf(editTextName.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                phone = String.valueOf(editTextPhone.getText());
                address = String.valueOf(editTextAddress.getText());

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(register.this, "您尚未輸入姓名，請重新輸入", Toast.LENGTH_SHORT).show();
                    return;
                }
                 if (TextUtils.isEmpty(email)) {
                     Toast.makeText(register.this, "您尚未輸入電子郵件，請重新輸入", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(password)) {
                    Toast.makeText(register.this, "您尚未輸入密碼，請重新輸入", Toast.LENGTH_SHORT).show();
                    return;
                 } else if (password.length()<6) {
                     Toast.makeText(register.this, "密碼數字需至少輸入六位數", Toast.LENGTH_SHORT).show();
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
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                                                        test = findViewById(R.id.test);
                                                        Toast.makeText(register.this, "创建帐号失败: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        test.setText(task.getException().getMessage());
                                                    }
                                                }
                                            });
                                } else {
                                    // 如果注册失败，向用户显示消息
                                    Toast.makeText(register.this, "创建帐号失败.", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(register.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
             }
        });
    }
}