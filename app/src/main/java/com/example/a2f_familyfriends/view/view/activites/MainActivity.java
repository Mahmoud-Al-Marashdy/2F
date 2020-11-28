package com.example.a2f_familyfriends.view.view.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a2f_familyfriends.R;
import com.example.a2f_familyfriends.view.adapter.ViewPagerAdapter;
import com.example.a2f_familyfriends.view.view.activites.phone.PhoneSingUp;
import com.example.a2f_familyfriends.view.view.fragmentsMain.Contacts;
import com.example.a2f_familyfriends.view.view.fragmentsMain.Messages;
import com.example.a2f_familyfriends.view.view.fragmentsMain.Profile;
import com.example.a2f_familyfriends.view.view.fragmentsMain.Stories;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Button LogOut;
    ViewPager2 viewPager;
    Fragment[] fragmentsMain;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentuser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

//ViewPager And Main Fragments
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.mainTabs);
        fragmentsMain = new Fragment[] {new Profile(),new Messages(),new Stories(),new Contacts()};
        adapter = new ViewPagerAdapter(this, fragmentsMain);
        viewPager.setAdapter(adapter);
        String[] tabsname = {"","Messages","Stories","Contacts"};
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                tab.setText(tabsname[position]);

            }
        });
        tabLayoutMediator.attach();

        LogOut = findViewById(R.id.logOutBtn);

     }

    @Override
    protected void onStart() {
        super.onStart();

        mCurrentuser = mAuth.getCurrentUser();

        if (mCurrentuser == null){
            Intent SingUpIntent = (new Intent(this, PhoneSingUp.class));
            SingUpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SingUpIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(SingUpIntent);
            finish();
        }

    }


}



