package com.example.a2f_familyfriends.view.view.fragmentsMain;

import android.os.Bundle;

import com.example.a2f_familyfriends.R;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Profile extends Fragment {

    Button settingsBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V1 = inflater.inflate(R.layout.fragment_profile, container, false);

        return V1;
    }
}