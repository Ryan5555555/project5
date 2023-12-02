package com.example.project5;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    TextInputEditText editTextEmail,getEditTextPassword;
    FirebaseAuth mAuth;
    Button register, login;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        如果有帳號就直接進去
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),home.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        getEditTextPassword = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        GlobalVariable globalVariable = (GlobalVariable) getApplication();
        globalVariable.loadSwitchState(this);
//        findViewById應該放在 setContentView(R.layout.activity_main); 之後


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在点击事件内设置按钮为灰色
                register.setEnabled(false);

                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
                finish();

                // 在点击后设置按钮为灰色并延迟一段时间后恢复
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        register.setEnabled(true);
                    }
                }, 100); // 100毫秒，可以根据需要调整延迟的时间
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setEnabled(false);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(getEditTextPassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "您尚未輸入電子郵件，請重新輸入", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "您尚未輸入密碼，請重新輸入", Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "成功登入", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "登入失敗",
                                            Toast.LENGTH_SHORT).show();
                                    Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        });
                // 在点击后设置按钮为灰色并延迟一段时间后恢复
                login.setEnabled(false);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        login.setEnabled(true);
                    }
                }, 100); // 1000毫秒 = 1秒，可以根据需要调整延迟的时间
                 }
        });
    }
}



