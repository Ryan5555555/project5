package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class chat extends AppCompatActivity {

    FirebaseUser fuser;
    DatabaseReference reference;
    ImageView btn_send;
    EditText text_send;
    FirebaseAuth auth;
    FirebaseUser user;
    Intent intent;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        auth = FirebaseAuth.getInstance(); // 初始化 FirebaseAuth 物件

        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        intent = getIntent();

        user = auth.getCurrentUser(); // 獲取當前用戶，
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        String managerid =  "5U1Z69VEysXbuC4r0HleEpa2fQU2";
//        檢查  managerid 就是 receiver的id 可以到firebase database選擇id當作manager
        reference = FirebaseDatabase.getInstance().getReference("Users").child(managerid);

        //按發送按鈕
        btn_send.setOnClickListener(v -> {
            String msg = text_send.getText().toString();

            if (!msg.equals("")) {
                sendMessage(fuser.getUid(), managerid, msg);
            } else {
                Toast.makeText(chat.this, "尚未輸入", Toast.LENGTH_SHORT).show();
            }
            text_send.setText("");
        });
    }

    private void sendMessage(String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashmap = new HashMap<>();

        hashmap.put("sender", sender);
        hashmap.put("receiver", receiver);
        hashmap.put("message", message);

        reference.child("Chats").push().setValue(hashmap);
        //試做firebase store db
        db.collection("hashmap")
                .add(hashmap)
                .addOnSuccessListener(documentReference -> Toast.makeText(chat.this,  "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(chat.this, "創建id未成功" , Toast.LENGTH_SHORT).show());
    }
}
