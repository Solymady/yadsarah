package com.example.yadsarah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class donor extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DonorAboutFragment donorAboutFragment = new DonorAboutFragment();
    DonateMFragment donateMFragment = new DonateMFragment();

    ThanksFragment thanksFragment = new ThanksFragment();

    ContactFragment contactFragment = new ContactFragment();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);


        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,donorAboutFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.about:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, donorAboutFragment).commit();
                        return true;
                    case R.id.Donate:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, donateMFragment).commit();
                        return true;
                    case R.id.Thanks:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, thanksFragment).commit();
                        return true;
                    case R.id.ContactUs:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, contactFragment).commit();
                        return true;
                }

                return false;
            }
        });

    }
}