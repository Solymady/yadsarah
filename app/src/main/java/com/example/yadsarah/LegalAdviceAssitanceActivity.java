package com.example.yadsarah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class LegalAdviceAssitanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_advice_assistance);
    }

    // Function to create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    // Function to handle the selection of menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Check if the selected menu item is "units"
        if(id == R.id.units){
            // Create an intent to start the "AccommodationUnits" activity
            Intent intent = new Intent(LegalAdviceAssitanceActivity.this, AccommodationUnitsActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.healthyActivity){
            // Create an intent to start the "CenteroFHealthyActivity" activity
            Intent intent = new Intent(LegalAdviceAssitanceActivity.this, CenteroFHealthyActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.assistance){
            // Create an intent to start the "LegalAdviceAssistance" activity (itself)
            Intent intent = new Intent(LegalAdviceAssitanceActivity.this, LegalAdviceAssitanceActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
