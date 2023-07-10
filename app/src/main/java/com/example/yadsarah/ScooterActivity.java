package com.example.yadsarah;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yadsarah.Database.WalkOrderContract;

public class ScooterActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageView walkImageView;
    ImageButton walkPlusquantity, walkMinusquantity;
    TextView walkQuantitynumber, walkToolName, walkToolPrice;
    CheckBox homeShipping, pickUp;
    Button walkAddtoCart;
    int quantity;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_info);

        // Initialize views
        walkImageView = findViewById(R.id.walkViewInfoImg);
        walkPlusquantity = findViewById(R.id.walkAddquantity);
        walkMinusquantity = findViewById(R.id.walkSubquantity);
        walkQuantitynumber = findViewById(R.id.walkQuantity);
        walkToolName = findViewById(R.id.walkToolNameinInfo);
        walkToolPrice = findViewById(R.id.walkToolPrice);
        homeShipping = findViewById(R.id.walkHomeShipping);
        walkAddtoCart = findViewById(R.id.walkAddtocart);
        pickUp = findViewById(R.id.walkPickUp);

        // Set the name and image of the tool
        walkToolName.setText("Scooter");
        walkImageView.setImageResource(R.drawable.scooter_img);

        // Button click listener for adding to cart
        walkAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCart(); // Save the item to the cart
                Intent intent = new Intent(ScooterActivity.this, SummaryActivity.class);
                startActivity(intent); // Start the summary activity
            }
        });

        // Button click listener for increasing quantity
        walkPlusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = 100;
                quantity++;
                displayQuantity(); // Update the displayed quantity
                updateToolPrice(); // Update the tool price based on quantity and checkboxes
            }
        });

        // Button click listener for decreasing quantity
        walkMinusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = 100;
                // Ensure the quantity doesn't go below 0
                if (quantity == 0) {
                    Toast.makeText(ScooterActivity.this, "Can't decrease quantity below zero", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity(); // Update the displayed quantity
                    updateToolPrice(); // Update the tool price based on quantity and checkboxes
                }
            }
        });

        // Checkbox click listener for home shipping
        homeShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeShipping.isChecked()) {
                    pickUp.setChecked(false);
                    updateToolPrice(); // Update the tool price based on checkboxes
                }
            }
        });

        // Checkbox click listener for pick-up
        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickUp.isChecked()) {
                    homeShipping.setChecked(false);
                    updateToolPrice(); // Update the tool price based on checkboxes
                }
            }
        });
    }

    // Save the item to the cart
    private void saveCart() {
        // Get the values from views
        String name = walkToolName.getText().toString();
        String price = walkToolPrice.getText().toString();
        String quantity = walkQuantitynumber.getText().toString();

        // Create a ContentValues object with the values
        ContentValues values = new ContentValues();
        values.put(WalkOrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(WalkOrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(WalkOrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        // Check if pick-up or home shipping is selected and add to ContentValues accordingly
        if (pickUp.isChecked()) {
            values.put(WalkOrderContract.OrderEntry.COLUMN_PICKUP, "Confirmed PickUp");
        } else {
            values.put(WalkOrderContract.OrderEntry.COLUMN_PICKUP, "Confirmed home shipping");
        }

        if (homeShipping.isChecked()) {
            values.put(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING, "Confirmed home shipping");
        } else {
            values.put(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING, "Not Confirmed home shipping");
        }

        // Insert the values into the database using a ContentResolver
        Uri newUri = getContentResolver().insert(WalkOrderContract.OrderEntry.CONTENT_URI, values);

        // Show a toast message based on the insertion result
        if (newUri == null) {
            Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Success adding to Cart", Toast.LENGTH_SHORT).show();
        }

        hasAllRequiredValues = true;
    }

    // Calculate the price based on the quantity and checkboxes
    private void updateToolPrice() {
        int basePrice = 100;

        if (homeShipping.isChecked()) {
            basePrice += 10;
        }

        int toolPrice = basePrice * quantity;
        String setNewPrice = "$ " + String.valueOf(toolPrice);
        walkToolPrice.setText(setNewPrice);
    }

    // Display the current quantity
    private void displayQuantity() {
        walkQuantitynumber.setText(String.valueOf(quantity));
    }

    // LoaderManager.LoaderCallbacks implementation

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

        Uri uri = Uri.parse("content://com.example.yadsarah");

        return new CursorLoader(this, uri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int toolname = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_NAME);
            int toolprice = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_PRICE);
            int toolquantity = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_QUANTITY);
            int toolhomeshipping = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING);
            int toolpickup = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_PICKUP);

            String nameoftool = cursor.getString(toolname);
            String priceoftool = cursor.getString(toolprice);
            String quantityoftool = cursor.getString(toolquantity);
            String homeshipping = cursor.getString(toolhomeshipping);
            String pickup = cursor.getString(toolpickup);

            // Update the views with the retrieved data
            walkToolName.setText(nameoftool);
            walkToolPrice.setText(priceoftool);
            walkQuantitynumber.setText(quantityoftool);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Clear the views
        walkToolName.setText("");
        walkToolPrice.setText("");
        walkQuantitynumber.setText("");
    }
}
