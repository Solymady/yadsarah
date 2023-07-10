package com.example.yadsarah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private Button btnSalary;
    private Button b1;
    private Button b2;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnSalary = view.findViewById(R.id.buttonSalary);

        btnSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SalaryCalculator.class);
                startActivity(intent);
            }
        });
        b1 = view.findViewById(R.id.shift);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), employeeAssibment.class);
                startActivity(intent);
            }
        });

        b2 = view.findViewById(R.id.shiftview);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), viewEmployeeSc.class);
                startActivity(intent);
            }
        });

        return view;
    }
}