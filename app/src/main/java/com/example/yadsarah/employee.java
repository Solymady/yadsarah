package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class employee extends AppCompatActivity {


        BottomNavigationView bottomNavigationView;
        HomeFragment homeFragment = new HomeFragment();


        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_employee);

            bottomNavigationView  = findViewById(R.id.bottom_navigation);

            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();



// This function is called when a menu item is selected from the bottom navigation view. It replaces the current fragment with the corresponding fragment based on the selected menu item.
            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                            return true;
                    }

                    return false;
                }
            });

        }
    }



