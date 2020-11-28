package com.example.a2f_familyfriends.view.view.fragmentsMain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2f_familyfriends.R;
import com.example.a2f_familyfriends.view.adapter.ChatListAdapter;
import com.example.a2f_familyfriends.view.model.ChatList;

import java.util.ArrayList;
import java.util.List;

public class Messages extends Fragment {


    public Messages() {
        // Required empty public constructor
    }


    public List<ChatList> list = new ArrayList<>();
    public RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        recyclerView = view.findViewById(R.id.chatListRec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getChatList();

        return view;
    }

    public void getChatList() {
        list.add(new ChatList("","","","",""));
        recyclerView.setAdapter(new ChatListAdapter(list,getContext()));
    }
}