package com.example.a2f_familyfriends.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a2f_familyfriends.R;
import com.example.a2f_familyfriends.view.model.ChatList;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.Holder> {


    public List<ChatList> list;
    public Context context;

    public ChatListAdapter(List<ChatList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_list_layout,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        ChatList chatList = list.get(position);

        Glide.with(context).load(chatList.getProfileIMG()).into(holder.profileIMG);
        holder.name.setText(chatList.getUserName());
        holder.message.setText(chatList.getMessage());
        holder.time.setText(chatList.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView name , message , time ;
        public CircularImageView profileIMG;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.userName);
            message = itemView.findViewById(R.id.chatMessage);
            time = itemView.findViewById(R.id.chatTime);
            profileIMG = itemView.findViewById(R.id.chatProfileImg);
        }
    }
}
