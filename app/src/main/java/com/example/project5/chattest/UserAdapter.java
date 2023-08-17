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

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context mContext,List<User> mUsers){
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
//        從指定的user_item.xml佈局檔案中創建一個視圖
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.username.setText("病害專家");
    }

    @Override
    public int getItemCount() {
        return  mUsers.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
            public TextView username;//使用者姓名
            public ImageView profile_image;//使用者頭像

            public ViewHolder(View itemView){
                super( itemView);

                username = itemView.findViewById(R.id.username);
                profile_image = itemView.findViewById(R.id.profile_image);
            }
        }
}
