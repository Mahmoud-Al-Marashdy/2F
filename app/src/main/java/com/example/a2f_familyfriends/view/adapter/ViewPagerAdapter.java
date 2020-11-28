package com.example.a2f_familyfriends.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    Fragment[] fragmentsMain;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Fragment[] fragmentsMain) {
        super(fragmentActivity);
        this.fragmentsMain = fragmentsMain;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentsMain[position];
    }

    @Override
    public int getItemCount() {
        return fragmentsMain.length;
    }
}
