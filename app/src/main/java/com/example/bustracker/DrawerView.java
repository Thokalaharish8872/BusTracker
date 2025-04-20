package com.example.bustracker;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DrawerView extends AppCompatActivity {

    static DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drawer_view);


        NavigationView View = findViewById(R.id.NavigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
//        Toast.makeText(ActivityForFragments.this,"hello",Toast.LENGTH_SHORT).show();

        MenuItem item = View.getMenu().getItem(0);

        SetFrame(new HomeFragment(),true,item);


        View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(DrawerView.this,"oh no",Toast.LENGTH_SHORT).show();

                if(item.getItemId()==R.id.Home) SetFrame(new HomeFragment(),false,item);
                if(item.getItemId()==R.id.Directions) SetFrame(new DirectionsFragment(),false,item);
                if(item.getItemId()==R.id.Favourites) SetFrame(new FavoritesFragment(),false,item);
                if(item.getItemId()==R.id.Rewards) SetFrame(new AwardsFragment(),false,item);
                if(item.getItemId()==R.id.More) SetFrame(new MoreFragment(),false,item);

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }

    private void SetFrame(Fragment fragment, boolean flag, MenuItem item) {
        Toast.makeText(DrawerView.this,"came",Toast.LENGTH_SHORT).show();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(flag){
            ft.add(R.id.FrameLayout,fragment);
        }
        else {
            ft.replace(R.id.FrameLayout, fragment);
        }
        ft.commit();
    }

    static void more(){
        drawerLayout.openDrawer(GravityCompat.START);
    }
}