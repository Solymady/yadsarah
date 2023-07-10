package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class mainManager extends AppCompatActivity {


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    private Button button6;
    private Button button7;

    ImageView logo;
    Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);
        //Image view
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setImageResource(R.drawable.download);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton1();

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton2();

            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton3();

            }
        });
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton5();
            }
        });

        button6=findViewById(R.id.addjob);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton6();
            }
        });

        button4=findViewById(R.id.viewemployye);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton4();
            }
        });


        button7=findViewById(R.id.viewVol);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityButton7();
            }
        });

    }






    private void openActivityButton1() {
        Intent intent=new Intent(this, shiftWorker.class);
        startActivity(intent);

    }

    private void openActivityButton2() {
        Intent intent=new Intent(this, shiftVolunteer.class);
        startActivity(intent);

    }

    private void openActivityButton3() {
        Intent intent=new Intent(this, EmployeeData.class);
        startActivity(intent);
    }



    private void openActivityButton5() {
       Intent intent=new Intent(this, viewVolAsbmit.class);
       startActivity(intent);

   }

    private void openActivityButton4() {
        Intent intent=new Intent(this, viewEmplooyeAssbmint.class);
        startActivity(intent);

    }

    private void openActivityButton6() {
        Intent intent=new Intent(this, addJob.class);
        startActivity(intent);

    }

    private void openActivityButton7() {
        Intent intent=new Intent(this, viewVolAsbmit.class);
        startActivity(intent);

    }
}
