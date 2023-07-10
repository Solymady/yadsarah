package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class DonateMoney extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_money);
        myDialog = new Dialog(this);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        Button donateButton = findViewById(R.id.button_apply);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }
        });
    }



   // This function is called when a radio button is selected from the radio group. It retrieves the selected radio button and displays a toast message showing the selected button's text.
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

    // This function displays a custom dialog box with the layout donatemoneypopup. It sets up a click listener for the close button in the dialog and shows the dialog.
    public void ShowPopup() {
        myDialog.setContentView(R.layout.donatemoneypopup);
        TextView txtclose = myDialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}










