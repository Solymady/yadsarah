package com.example.yadsarah;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.yadsarah.Adaptor.walkingOrderAdaptor;

import java.util.ArrayList;
import java.util.List;

public class WalkingAccessoriesActivity extends AppCompatActivity {

    List<WalkingAccessoriesModelActivity> walkModelList;
    RecyclerView walkRecyclerView;
    walkingOrderAdaptor wAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking_accessories);

        // Create a list of WalkingAccessoriesModelActivity objects
        walkModelList = new ArrayList<>();
        walkModelList.add(new WalkingAccessoriesModelActivity("tilt_back_wheelchair", getString(R.string.tilt_back_wheelchair), R.drawable.tilt_back_wheelchair_img));
        walkModelList.add(new WalkingAccessoriesModelActivity("scooter", getString(R.string.scooter), R.drawable.scooter_img));
        walkModelList.add(new WalkingAccessoriesModelActivity("treadmill_for_adults", getString(R.string.treadmill_for_adults), R.drawable.treadmill_for_adults_img));
        walkModelList.add(new WalkingAccessoriesModelActivity("children_wheelchair", getString(R.string.children_wheelchair), R.drawable.children_wheelchair_img));
        walkModelList.add(new WalkingAccessoriesModelActivity("elevator", getString(R.string.elevator), R.drawable.elevator_img));
        walkModelList.add(new WalkingAccessoriesModelActivity("canadian_crutches", getString(R.string.canadian_crutches), R.drawable.canadian_crutches_img));

        // Initialize the RecyclerView
        walkRecyclerView = findViewById(R.id.walkingActivityRecyclerView);
        walkRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create and set the adapter for the RecyclerView
        wAdapter = new walkingOrderAdaptor(this, walkModelList);
        walkRecyclerView.setAdapter(wAdapter);
    }
}
