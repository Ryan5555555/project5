package com.example.project5.chattest;

import static androidx.camera.core.CameraXThreads.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project5.R;
import com.example.project5.chat;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URI;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MessageAdapter extends  RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public  static  final int MSG_TYPE_LEFT = 0;//接收者
    public  static  final int MSG_TYPE_RIGHT = 1;//發送者
    public static final int MSG_TYPE_IMAGE = 2;
    public static final int MSG_TYPE_TEXT = 3;
    public Uri duri;

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


        long currentTimeMillis = chat.getTimestamp().toDate().getTime();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference date = storageReference.child("chat_images/"+ currentTimeMillis);
        date.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri downloadUrl)
            {
              duri = downloadUrl;
            }
        });


        if (chat.getMessage() == null) {
            holder.show_img.setVisibility(View.VISIBLE);
            holder.show_message.setVisibility(View.GONE);
            System.out.println(chat.getTimestamp().toDate());
            System.out.println(System.currentTimeMillis());
            System.out.println(chat.getimage());
            //holder.show_img.setImageURI(Uri.parse(chat.getimage()));
            // 使用 Glide 來載入圖片
            //try {
           // Uri uri = Uri.parse(chat.getimage());
           // URI javaUri = URI.create(uri.toString());
          //  URL url = javaUri.toURL();
            Glide.with(mContext).load(chat.getimage()).into(holder.show_img);
        //} catch (IOException e) {
          //  Log.e("OnBindImg", "load url error", e);
        //}
            //holder.show_img.setImageBitmap(getImageBitmap(chat.getimage()));

            holder.time2.setVisibility(View.VISIBLE);
            holder.time.setVisibility(View.GONE);
        }
        else if (chat.getMessage() != null)
        {
            holder.show_img.setVisibility(View.GONE);
            holder.show_message.setVisibility(View.VISIBLE);
            holder.show_message.setText(chat.getMessage());
            holder.time.setVisibility(View.VISIBLE);
            holder.time2.setVisibility(View.GONE);
        }
        //else{holder.show_message.setText("error:訊息傳送失敗");}

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
                if(TextOrImg(position) == MSG_TYPE_IMAGE) {
                    if (amPm.equals("AM")) {
                        holder.time2.setText("上午 " + timeParts[0]);
                    } else {
                        holder.time2.setText("下午 " + timeParts[0]);
                    }
                }else{
                    if (amPm.equals("AM")) {
                        holder.time.setText("上午 " + timeParts[0]);
                    } else {
                        holder.time.setText("下午 " + timeParts[0]);
                    }
                }
            } else {
                if(TextOrImg(position) == MSG_TYPE_IMAGE) {
                    if (amPm.equals("AM")) {
                        holder.time2.setText("上午 " + timeParts[0]);
                    } else {
                        holder.time2.setText("下午 " + timeParts[0]);
                    }
                }else{
                    if (amPm.equals("AM")) {
                        holder.time.setText("上午 " + timeParts[0]);
                    } else {
                        holder.time.setText("下午 " + timeParts[0]);
                    }
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
        public TextView time2;
        public ImageView profile_image;

        public ViewHolder(View itemView){
            super( itemView);
            time = itemView.findViewById(R.id.time);
            time2 = itemView.findViewById(R.id.time2);
            show_message = itemView.findViewById(R.id.show_message);
            show_img = itemView.findViewById(R.id.show_img);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public  int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(fuser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }

    public int TextOrImg(int position){
        if(mChat.get(position).getMessage() == null) {
            return MSG_TYPE_IMAGE; //用於顯示圖片的 View Type
        }else{
            return MSG_TYPE_TEXT; //用於顯示文字的 View Type
        }
    }

    private Bitmap getImageBitmap(String Img) {
        Bitmap bm = null;
        try {
            Uri uri = Uri.parse(Img);
            URI javaUri = URI.create(uri.toString());
            URL url = javaUri.toURL();
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Bitmap error", "Error getting bitmap", e);
        }
        return bm;
    }

}

