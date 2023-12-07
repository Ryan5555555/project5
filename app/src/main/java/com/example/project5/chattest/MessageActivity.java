package com.example.project5.chattest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.R;
import com.example.project5.home;
import com.example.project5.user;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    ImageView btn_send,home,choose;
    EditText text_send;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    MessageAdapter messageAdapter;
    List<Chat> mchat;
    RecyclerView recyclerView;
    Intent intent;
    String managerid = "5U1Z69VEysXbuC4r0HleEpa2fQU2";// managerid 就是 receiver 的 id 可以到 firebase database 選擇 id 當作 manager
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messageactivity);
        auth = FirebaseAuth.getInstance(); // 初始化 FirebaseAuth 物件
        user = auth.getCurrentUser(); // 獲取當前用戶

        choose = findViewById(R.id.choose);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
        home = findViewById(R.id.home);
        intent = getIntent();


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);// 確保列表從底部開始，顯示最新的消息
        recyclerView.setLayoutManager(linearLayoutManager);
        mchat = new ArrayList<>();//用來存儲讀取到的聊天消息
        messageAdapter = new MessageAdapter(this, mchat);
        recyclerView.setAdapter(messageAdapter);

        readMessage(user.getUid(), managerid);


        home.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(com.example.project5.chattest.MessageActivity.this, home.class);//目前Activity與目標Activity
            startActivity(intent);
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,2);
            }
        });

        // 步驟1 按發送按鈕
        btn_send.setOnClickListener(v -> {
            String msg = text_send.getText().toString(); // msg 為發送框內輸入的文字
            if (!msg.equals("")) {
                sendMessage(user.getUid(), managerid, msg,null);
            } else {
                Toast.makeText(MessageActivity.this, "尚未輸入", Toast.LENGTH_SHORT).show();
            }
            text_send.setText("");
        });
    }

    // 步驟2
    private void sendMessage(String sender, String receiver, String message, Uri imageUri) {
        // 在這裡添加將訊息寫入 Firestore 的程式碼
        // 您可以使用 FirebaseFirestore 的 collection 和 document 來組織資料
        HashMap<String, Object> hashmap = new HashMap<>();

        hashmap.put("sender", sender);
        hashmap.put("receiver", receiver);
        hashmap.put("timestamp", FieldValue.serverTimestamp());

        //if(message != null){
            hashmap.put("message", message);
        //}else{hashmap.put("message", "");}

        if (imageUri != null)
        {
            hashmap.put("image", imageUri.toString());
        }

        // 創建一個新的訊息文件
        // 將訊息寫入到 Firestore 的 "Chats" 集合中
        db.collection("Chats").add(hashmap)
                .addOnSuccessListener(documentReference -> {
                    // 在消息成功发送后再次调用 readMessage 函数
                    readMessage(user.getUid(), managerid);
                })
                .addOnFailureListener(e -> Toast.makeText(MessageActivity.this, "傳送訊息未成功，請聯絡工作人員" , Toast.LENGTH_SHORT).show());
    }


    // 步驟3
    private void readMessage(String senderid, String receiverid) {
        db.collection("Chats")
                .whereEqualTo("sender", senderid)
                .whereEqualTo("receiver", receiverid)
                .orderBy("timestamp", Query.Direction.ASCENDING) // 根据时间戳升序排序
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        // 讀取失敗處理
                        return;
                    }

                    if (queryDocumentSnapshots != null) {
                        mchat.clear();
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            Chat chat = snapshot.toObject(Chat.class);
                            if (chat.getReceiver().equals(senderid) && chat.getSender().equals(receiverid) ||
                                    chat.getReceiver().equals(receiverid) && chat.getSender().equals(senderid)) {
                                mchat.add(chat);
                            }
                        }

                        messageAdapter = new MessageAdapter(MessageActivity.this, mchat);
                        recyclerView.setAdapter(messageAdapter);
                    }
                });
    }

    private void sendImageMessage(String sender, String receiver, Uri imageUri) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference date = storageReference.child("chat_images/"+FieldValue.serverTimestamp());
        UploadTask uploadTask = date.putFile(imageUri);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return date.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    sendMessage(sender,receiver,null,downloadUri);
                } else {
                    // Handle failures
                    Toast.makeText(MessageActivity.this , "傳送失敗!",Toast.LENGTH_SHORT).show();
                }
            }
        });





        //date.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        //    @Override public void onSuccess(Uri uri) {
                // 在這裡處理獲取到的下載網址(uri)
        //        sendMessage(sender, receiver,null, uri);
        //    }
        //});

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            Log.e(this.getClass().getName(),"Result:" + data.toString());
            if (data != null){
                Uri uri = data.getData();
                Log.e(this.getClass().getName(),"Uri:"+String.valueOf(uri));
                sendImageMessage(user.getUid(), managerid, uri);
            }
        }
    }
}
