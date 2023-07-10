package com.example.yadsarah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yadsarah.Adaptor.CategoryAdaptor;
import com.example.yadsarah.Domain.CategoryDomain;

import java.util.ArrayList;


public class RentToolsActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_tools);

        // Set up the RecyclerView for displaying categories
        recyclerViewCategory();
    }

    /**
     * Sets up the RecyclerView to display categories
     */
    private void recyclerViewCategory() {
        // Create a linear layout manager with horizontal orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        // Create a list of category items
        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Walking and mobility accessories", "cat_1"));
        category.add(new CategoryDomain("Bath and toilet", "cat_2"));
        category.add(new CategoryDomain("Medical equipment", "cat_3"));
        category.add(new CategoryDomain("Home hospitalization and hospital accessories", "cat_4"));
        category.add(new CategoryDomain("Mothers and babies", "cat_5"));
        category.add(new CategoryDomain("Beds and mattresses", "cat_6"));
        category.add(new CategoryDomain("Respiration", "cat_7"));
        category.add(new CategoryDomain("Physiotherapy", "cat_8"));

        // Create and set the adapter for the RecyclerView
        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}
