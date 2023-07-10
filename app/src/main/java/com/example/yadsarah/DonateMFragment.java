package com.example.yadsarah;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DonateMFragment extends Fragment {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Dialog myDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donate_m, container, false);

        myDialog = new Dialog(getActivity());
        radioGroup = view.findViewById(R.id.radioGroup);

        Button donateButton = view.findViewById(R.id.button_apply);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        return view;
    }

    // This function is called when a radio button is selected from the radio group. It retrieves the selected radio button and displays a toast message showing the selected button's text.
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = getView().findViewById(radioId);

        Toast.makeText(getActivity(), "Selected Radio Button: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    // This function displays a custom dialog box with the layout donatemoneypopup. It sets up a click listener for the close button in the dialog and shows the dialog.
    public void showPopup() {
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

