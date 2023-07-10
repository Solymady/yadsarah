package com.example.yadsarah;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button buttonNext = findViewById(R.id.buttonToServices);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the second activity
                Intent intent = new Intent(AboutActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_contact_accessibility, menu);


        return true;
    }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.about) {
                Intent intent = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.accessbility) { //. It is called when a menu item is selected from the options menu.
                // Inside this method, the selected item's ID is retrieved
                // Then, based on the ID, different actions are performed
                Intent intent = new Intent(AboutActivity.this, AccessibilityStatementActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.contact) {
                Intent intent = new Intent(AboutActivity.this, ContactUsActivity.class);
                startActivity(intent);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }


