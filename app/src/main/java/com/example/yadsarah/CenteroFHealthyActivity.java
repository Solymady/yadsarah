package com.example.yadsarah;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class CenteroFHealthyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centero_fhealthy);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //The onOptionsItemSelected(MenuItem item) function is called when a menu item is selected from the options menu. It handles the actions to be performed based on the selected item.
        //
        int id = item.getItemId();  // This line retrieves the ID of the selected menu item

        if(id == R.id.units){ //This condition checks if the selected menu item's ID matches R.id.units. If it does, the code inside the block is executed.
            Intent intent = new Intent(CenteroFHealthyActivity.this,AccommodationUnitsActivity.class); // This line creates an intent to start the "AccommodationUnits" activity.
            startActivity(intent);
            return true;
        }
        else
        if(id == R.id.healthyActivity){
            Intent intent = new Intent(CenteroFHealthyActivity.this,CenteroFHealthyActivity.class);
            startActivity(intent);
            return true;
        }
        else
        if(id == R.id.assistance){
            Intent intent = new Intent(CenteroFHealthyActivity.this,LegalAdviceAssitanceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item); // This line is executed if none of the above conditions are met.
        // It calls the superclass's implementation of the onOptionsItemSelected method, which handles the selection of other menu items that are not specifically handled in this function.
    }
}
