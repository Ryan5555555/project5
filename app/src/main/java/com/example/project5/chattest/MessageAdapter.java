package com.example.project5.chattest;

import android.content.Context;
import android.net.Uri;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
        Chat chat = mChat.get(position);

        holder.show_message.setText(chat.getMessage());
        holder.show_img.setImageURI(Uri.parse(chat.getImg()));

        // 格式化时间戳为台湾时间
        if (chat.getTimestamp() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            TimeZone taiwanTimeZone = TimeZone.getTimeZone("Asia/Taipei");
            sdf.setTimeZone(taiwanTimeZone);
            String time = sdf.format(chat.getTimestamp().toDate()); // 将时间戳转换为日期对象并进行格式化

            String[] timeParts = time.split(" ");
            String amPm = timeParts[1];

            // 根据消息发送者来确定显示在左边还是右边
            if (getItemViewType(position) == MSG_TYPE_LEFT) {
                if (amPm.equals("AM")) {
                    holder.time.setText("上午 " + timeParts[0]);
                } else {
                    holder.time.setText("下午 " + timeParts[0]);
                }
            } else {
                if (amPm.equals("AM")) {
                    holder.time.setText("上午 " + timeParts[0]);
                } else {
                    holder.time.setText("下午 " + timeParts[0]);
                }
            }
        } else {
            holder.time.setText("時間錯誤");
        }
        
    }

    @Override
    public int getItemCount() {
        return  mChat.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView show_img;
        public TextView time;
        public ImageView profile_image;

        public ViewHolder(View itemView){
            super( itemView);
            time = itemView.findViewById(R.id.time);
            show_message = itemView.findViewById(R.id.show_message);
            show_img = itemView.findViewById(R.id.show_img);
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

