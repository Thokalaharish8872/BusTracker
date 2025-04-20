package com.example.bustracker;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityForFragments extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    DirectionsFragment directionsFragment = new DirectionsFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();
    AwardsFragment awardsFragment = new AwardsFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_fragments);

        BottomNavigationView btmView = findViewById(R.id.BottomNavigationView);
        MenuItem item = btmView.getMenu().getItem(0);
        SetFrame(homeFragment,true,item);

        btmView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if(item.getItemId() == R.id.Home) SetFrame(homeFragment,false,item);
                    else if(item.getItemId() == R.id.Directions) SetFrame(directionsFragment,false,item);
                    else if(item.getItemId() == R.id.Favourites) SetFrame(favoritesFragment,false,item);
                    else if(item.getItemId() == R.id.Rewards) SetFrame(awardsFragment,false,item);
                    else SetFrame(profileFragment,false,item);
                    return true;
            }
        });
    }

     void SetFrame(Fragment fragment, boolean flag, MenuItem item) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if(flag) ft.add(R.id.FrameLayout,fragment);
        else ft.replace(R.id.FrameLayout, fragment);

        ft.commit();
        Drawable icon = item.getIcon();
        if(icon!=null){
            icon = icon.mutate();
            icon.setTint(getResources().getColor(R.color.red));
            item.setIcon(icon);
        }
    }

}