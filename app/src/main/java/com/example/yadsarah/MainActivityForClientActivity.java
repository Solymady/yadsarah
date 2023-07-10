package com.example.yadsarah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityForClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_client);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_contact_accessibility, menu);
        return true;
    }

    // Function to handle the selection of menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Check if the selected menu item is "about"
        if (id == R.id.about) {
            // Create an intent to start the "About" activity
            Intent intent = new Intent(MainActivityForClientActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.accessbility) {
            // Create an intent to start the "AccessibilityStatement" activity
            Intent intent = new Intent(MainActivityForClientActivity.this, AccessibilityStatementActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.contact) {
            // Create an intent to start the "ContactUs" activity
            Intent intent = new Intent(MainActivityForClientActivity.this, ContactUsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}