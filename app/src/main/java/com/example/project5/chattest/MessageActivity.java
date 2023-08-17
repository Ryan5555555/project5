package com.example.project5.chattest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    FirebaseUser fuser;
    DatabaseReference reference;
    ImageView btn_send;
    EditText text_send;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    MessageAdapter messageAdapter;
    List<Chat> mchat;
    RecyclerView recyclerView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messageactivity);
        auth = FirebaseAuth.getInstance(); // 初始化 FirebaseAuth 物件

        recyclerView =findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);// 確保列表從底部開始，顯示最新的消息
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);

        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        intent = getIntent();

        user = auth.getCurrentUser(); // 獲取當前用戶
        fuser = FirebaseAuth.getInstance().getCurrentUser();//表示已登錄的用戶

        mchat = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, mchat);
        recyclerView.setAdapter(messageAdapter);

        String managerid =  "5U1Z69VEysXbuC4r0HleEpa2fQU2";// managerid 就是 receiver的id 可以到firebase database選擇id當作manager
        reference = FirebaseDatabase.getInstance().getReference("Users").child(managerid);// *原本這裡是userid  指的是Firebase 實時數據庫的引用。該引用指向數據庫中的特定節點，“Users” 下的 managerid。
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                username
                readMessage(fuser.getUid(),managerid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //步驟1 按發送按鈕
        btn_send.setOnClickListener(v -> {
            String msg = text_send.getText().toString();//msg為發送框內輸入的文字
            if (!msg.equals("")) {
                sendMessage(user.getUid(), managerid, msg);
            } else {
                Toast.makeText(MessageActivity.this, "尚未輸入", Toast.LENGTH_SHORT).show();
            }
            text_send.setText("");
        });
    }

//步驟2
    private void sendMessage(String sender, String receiver, String message) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashmap = new HashMap<>();

        hashmap.put("sender", sender);
        hashmap.put("receiver", receiver);
        hashmap.put("message", message);

        reference.child("Chats").push().setValue(hashmap);//在Firebase資料庫中的"Chats"節點下，使用.push()方法創建一個新的子節點

        db.collection("Chats")
                .add(hashmap)
                .addOnSuccessListener(documentReference -> Toast.makeText(MessageActivity.this,  "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(MessageActivity.this, "創建id未成功" , Toast.LENGTH_SHORT).show());
    }

    //步驟3
    private  void readMessage(String  senderid ,String receiverid){
        mchat = new ArrayList<>();//用來存儲讀取到的聊天消息

        reference =FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() { //這個監聽器會在數據庫內容改變時觸發
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //  你使用 ValueEventListener 监听了 Chats 节点，一旦数据发生变化，会触发 onDataChange 方法
                Toast.makeText(MessageActivity.this, "資料改變" , Toast.LENGTH_SHORT).show();
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat =snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(senderid) &&  chat.getSender().equals(receiverid) ||
                            chat.getReceiver().equals(receiverid) && chat.getSender().equals(senderid)){
                        mchat.add(chat);
                    }

                    messageAdapter = new  MessageAdapter( MessageActivity.this,mchat);
                    recyclerView.setAdapter(messageAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
