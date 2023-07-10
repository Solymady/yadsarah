package com.example.yadsarah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class AccessibilityStatementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_statement);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_contact_accessibility, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //This function is called when a menu item is selected from the options menu.
        // It handles the actions to be performed based on the selected item.
        //Then, it checks the ID against different cases using if and else if statements. If the ID matches R.id.about, it creates an intent to start the "About" activity
        //Similarly, for other menu items such as R.id.accessibility and R.id.contact, it creates intents to start their respective activities
        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent = new Intent(AccessibilityStatementActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.accessbility) {
            Intent intent = new Intent(AccessibilityStatementActivity.this, AccessibilityStatementActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.contact) {
            Intent intent = new Intent(AccessibilityStatementActivity.this, ContactUsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}