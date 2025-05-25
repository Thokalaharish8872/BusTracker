package com.example.bustracker;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityForAdministrator extends AppCompatActivity {

    AdministatorHomeFragment homeFragment = new AdministatorHomeFragment();
    SelectStudentEditType studentFragment = new SelectStudentEditType();
    SelectDriverEditType driverFragment = new SelectDriverEditType();
    ProfileFragment profileFragment = new ProfileFragment();
    static boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_for_administrator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setFragment(homeFragment);

        BottomNavigationView navigationView = findViewById(R.id.BottomNavigationView);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.Home) setFragment(homeFragment);
                else if(item.getItemId() == R.id.Directions) setFragment(studentFragment);
                else if(item.getItemId() == R.id.Favourites) setFragment(driverFragment);
                else if(item.getItemId() == R.id.More) setFragment(profileFragment);
                return true;
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(!flag){
            ft.add(R.id.FrameLayout,fragment);
            flag = true;
        }
        else ft.replace(R.id.FrameLayout, fragment);
        ft.commit();
    }
}