package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText _txtUser, _txtPass;
    Button _btnLogin;
    Spinner _spinner;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtPass = findViewById(R.id.txtPass);
        _txtUser = findViewById(R.id.txtUser);
        _btnLogin = findViewById(R.id.btnLogin);
        _spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        // This function is called when the login button is clicked. It retrieves the selected item from the spinner and checks the entered username, password, and selected item to determine the appropriate action. It starts a new activity based on the selected user type: admin, client, employee, donor, or volunteer. If the credentials and selected user type don't match any of the predefined conditions, it displays an error toast.
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("admin")) {
                     Intent intent = new Intent(MainActivity.this, mainManager.class);
                     startActivity(intent);
                } else if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("Client")) {
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                }else if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("Employee")) {
                    Intent intent = new Intent(MainActivity.this, employee.class);
                    startActivity(intent);
                }else if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("Donor")) {
                    Intent intent = new Intent(MainActivity.this, donor.class);
                    startActivity(intent);
                }else if (_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin") && item.equals("volunteer")) {
                   Intent intent = new Intent(MainActivity.this, mainVolunteer.class);
                   startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}