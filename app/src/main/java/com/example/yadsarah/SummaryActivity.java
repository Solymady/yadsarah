package com.example.yadsarah;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.yadsarah.Adaptor.CartAdapter;
import com.example.yadsarah.Database.WalkOrderContract;

public class SummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private CartAdapter cartAdapter;
    private static final int LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Button clearDataButton = findViewById(R.id.walkClearthedatabase);

        // Set an OnClickListener to clear the database
        clearDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SummaryActivity.this);
                builder.setTitle("Clear Summary");
                builder.setMessage("Are you sure you want to clear the summary order?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User confirmed, delete the data
                        int deleteData = getContentResolver().delete(WalkOrderContract.OrderEntry.CONTENT_URI, null, null);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // Initialize the loader to retrieve data from the database
        LoaderManager.getInstance(this).initLoader(LOADER, null, this);

        ListView listView = findViewById(R.id.walkList);
        cartAdapter = new CartAdapter(this, null);
        listView.setAdapter(cartAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                WalkOrderContract.OrderEntry._ID,
                WalkOrderContract.OrderEntry.COLUMN_NAME,
                WalkOrderContract.OrderEntry.COLUMN_PRICE,
                WalkOrderContract.OrderEntry.COLUMN_QUANTITY,
                WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING,
                WalkOrderContract.OrderEntry.COLUMN_PICKUP
        };

        return new CursorLoader(this, WalkOrderContract.OrderEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Update the data in the adapter when the loading is finished
        cartAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Reset the data in the adapter when the loader is reset
        cartAdapter.swapCursor(null);
    }
}