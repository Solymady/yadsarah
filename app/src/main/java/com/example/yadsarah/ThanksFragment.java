package com.example.yadsarah;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ThanksFragment extends Fragment {

    private Dialog myDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanks, container, false);

        myDialog = new Dialog(getActivity());
        Button donateButton = view.findViewById(R.id.buttonthanks);
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        return view;

    }
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