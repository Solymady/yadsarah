package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class mainVolunteer extends AppCompatActivity {


    private Button button20;

    private Button button88 ;

    private Button job;

    ImageView logo;

    Drawable drawable;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_volunteer);
        //Image view
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setImageResource(R.drawable.download);

        button20 = findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton20();

            }
        });

        button88 = findViewById(R.id.button88);
        button88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton88();

            }
        });

        button88 = findViewById(R.id.job);
        button88.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton3();

            }
        });

    }

    private void openActivityButton20() {
        Intent intent=new Intent(this, viewShiftVol.class);
        startActivity(intent);

    }

    private void openActivityButton88() {
        Intent intent=new Intent(this, volunteerAssibment.class);
        startActivity(intent);

    }

    private void openActivityButton3() {
        Intent intent=new Intent(this, workSearch.class);
        startActivity(intent);

    }
    


}

