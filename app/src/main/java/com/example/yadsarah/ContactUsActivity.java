package com.example.yadsarah;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yadsarah.AccessibilityStatementActivity;
import com.example.yadsarah.R;

public class ContactUsActivity extends AppCompatActivity {

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThankYouDialog();
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
            Intent intent = new Intent(ContactUsActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.accessbility) {
            Intent intent = new Intent(ContactUsActivity.this, AccessibilityStatementActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.contact) {
            showThankYouDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showThankYouDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank You");
        builder.setMessage("Thank you for connecting with us. We will get back to you soon.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}