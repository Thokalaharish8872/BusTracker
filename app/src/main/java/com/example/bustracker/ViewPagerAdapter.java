package com.example.bustracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter  extends FragmentPagerAdapter {

    ActivityForFragments activityForFragments = new ActivityForFragments();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 : return new HomeFragment();
            case 1 : return new DirectionsFragment();
            case 2 : return new FavoritesFragment();
            case 3 : return new AwardsFragment();
            default: return new MoreFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Home";
            case 1 : return "Directions";
            case 2 : return "Favorites";
            case 3 : return "Rewards";
            default: return "More";
        }
    }

}
