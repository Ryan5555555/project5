package com.example.project5.chattest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public  static  final int MSG_TYPE_LEFT = 0;//接收者
    public  static  final int MSG_TYPE_RIGHT = 1;//發送者
    private Context mContext;//上下文
    private List<Chat> mChat;//聊天訊息的資料來源


    FirebaseUser fuser;//Firebase中的當前使用者


    public MessageAdapter(Context mContext,List<Chat> mChat){
        this.mChat  = mChat;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {


        Chat chat =mChat.get(position);

        holder.show_message.setText(chat.getMessage());
//        它通過mUsers清單中的使用者資料和mChat清單中的聊天訊息，將訊息內容設定到ViewHolder中的show_message元件上
    }

    @Override
    public int getItemCount() {
        return  mChat.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView profile_image;

        public ViewHolder(View itemView){
            super( itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public  int getItemViewType(int position){
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return  MSG_TYPE_LEFT;
        }

    }
}

