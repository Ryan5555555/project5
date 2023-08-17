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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class register extends AppCompatActivity {
    TextView textview;
    TextInputEditText editTextEmail,getEditTextPassword,editTextName;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    DatabaseReference reference;

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
        getEditTextPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String name,email, password;
                name = String.valueOf(editTextName.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(getEditTextPassword.getText());

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

                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's
                                FirebaseUser firebaseUser =mAuth.getCurrentUser();
                                assert firebaseUser != null;
                                String userid=firebaseUser.getUid();
                                reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
                                HashMap<String,String> hashmap =new HashMap<>();
                                hashmap.put("id",userid);

                                reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(register.this, "已創建帳號.",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),user.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(register.this, "創建帳號失敗.",
                                    Toast.LENGTH_SHORT).show();
                                Toast.makeText(register.this, task.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT).show();
                            }
                          }

                     });
             }
        });
    }
}