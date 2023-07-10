package com.example.yadsarah;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadsarah.Adaptor.ServicesAdaptor;
import com.example.yadsarah.Constants.MyConstants;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    RecyclerView recyclerViewMain;
    List<String> titles;
    List<Integer> images;
    ServicesAdaptor mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        recyclerViewMain = findViewById(R.id.recyclerViewMain);

        // Add titles and images for the services
        addAllTitles();
        addAllImages();

        // Create the adapter with the titles, images, and the current activity
        mainAdapter = new ServicesAdaptor(this, titles, images, ServicesActivity.this);

        // Set up the RecyclerView with a grid layout
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerViewMain.setLayoutManager(gridLayoutManager);
        recyclerViewMain.setAdapter(mainAdapter);

        // Set the item click listener on the adapter
        mainAdapter.setOnItemClickListener(new ServicesAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 3) { // Check if the clicked position is for Community Services
                    Intent intent = new Intent(ServicesActivity.this, CoomunityServiceActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    // Add titles for the services
    private void addAllTitles() {
        titles = new ArrayList<>();
        titles.add(MyConstants.MEDICAL_EQUIPMENT_RENTAL);
        titles.add(MyConstants.REHABILITATION_CENTER);
        titles.add(MyConstants.HOME_HOSPITALIZATION);
        titles.add(MyConstants.COMMUNITY_SERVICES);
    }

    // Add images for the services
    private void addAllImages() {
        images = new ArrayList<>();
        images.add(R.drawable.rent_tools_img);
        images.add(R.drawable.rehabilitation_center_img);
        images.add(R.drawable.homecare_img);
        images.add(R.drawable.community_serice_img);
    }
}
