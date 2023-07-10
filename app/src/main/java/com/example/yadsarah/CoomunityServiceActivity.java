package com.example.yadsarah;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadsarah.Adaptor.CommunityServicesAdaptor;
import com.example.yadsarah.Constants.MyConstants;

import java.util.ArrayList;
import java.util.List;

public class CoomunityServiceActivity extends AppCompatActivity {
    RecyclerView CommunityServicesRecycleView;
    List<String> headers;
    List<Integer> communityImages;
    CommunityServicesAdaptor mainAdaptor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coomunity_service);
        CommunityServicesRecycleView = findViewById(R.id.communictyRecycleView);

        // Initialize the list of headers for community services
        addAddTitlesToCommunityServices();

        // Initialize the list of images for community services
        addAllImagesToCommunityServices();

        // Create and set up the adapter for the RecyclerView
        mainAdaptor = new CommunityServicesAdaptor(this, headers, communityImages, CoomunityServiceActivity.this);

        // Create a GridLayoutManager with 2 columns and set it to the RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        CommunityServicesRecycleView.setLayoutManager(gridLayoutManager);
        CommunityServicesRecycleView.setAdapter(mainAdaptor);
    }

    // Function to add header titles for community services
    private void addAddTitlesToCommunityServices() {
        headers = new ArrayList<>();
        headers.add(MyConstants.LEGAL_ADVICE);
        headers.add(MyConstants.HEALTHY_CENTER);
        headers.add(MyConstants.ACCOMMODATION_UNITS);
    }

    // Function to add images for community services
    private void addAllImagesToCommunityServices() {
        communityImages = new ArrayList<>();
        communityImages.add(R.drawable.healthycenter);
        communityImages.add(R.drawable.lawadvice);
        communityImages.add(R.drawable.units);
    }
}

