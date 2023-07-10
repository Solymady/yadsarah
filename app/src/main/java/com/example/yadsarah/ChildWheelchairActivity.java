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

public class ChildWheelchairActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    ImageView walkImageView;
    ImageButton walkPlusquantity, walkMinusquantity;
    TextView walkQuantitynumber, walkToolName, walkToolPrice;
    CheckBox homeShipping, pickUp;
    Button walkAddtoCart;
    int quantity;
    boolean hasAllRequiredValues = false;

    private static final int ORDER_LOADER = 4;

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
        walkToolName.setText("Children Wheelchair");
        walkImageView.setImageResource(R.drawable.children_wheelchair_img);

        // Add to cart button click listener
        walkAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCart();
                Intent intent = new Intent(ChildWheelchairActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });

        // Increase quantity button click listener
        walkPlusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuantity();
                updatePrice();
            }
        });

        // Decrease quantity button click listener
        walkMinusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuantity();
                updatePrice();
            }
        });

        // Disable the other checkbox when one checkbox is checked
        pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pickUp.isChecked()) {
                    homeShipping.setChecked(false);
                }
                updatePrice();
            }
        });

        homeShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeShipping.isChecked()) {
                    pickUp.setChecked(false);
                }
                updatePrice();
            }
        });

        // Initialize the loader
        getLoaderManager().initLoader(ORDER_LOADER, null, this);
    }

    // Save the selected item to the cart
    private void saveCart() {
        // Get values from views
        String name = walkToolName.getText().toString();
        String price = walkToolPrice.getText().toString();
        String quantity = walkQuantitynumber.getText().toString();

        // Check if the quantity is zero
        if (Integer.parseInt(quantity) == 0) {
            Toast.makeText(this, "Quantity cannot be zero", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(WalkOrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(WalkOrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(WalkOrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        // Set pickup and home shipping values based on checkbox states
        if (pickUp.isChecked()) {
            values.put(WalkOrderContract.OrderEntry.COLUMN_PICKUP, "Confirmed PickUp");
        } else {
            values.put(WalkOrderContract.OrderEntry.COLUMN_PICKUP, "Not Confirmed PickUp");
        }

        if (homeShipping.isChecked()) {
            values.put(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING, "Confirmed home shipping");
        } else {
            values.put(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING, "Not Confirmed home shipping");
        }

        Uri contentUri = WalkOrderContract.OrderEntry.CONTENT_URI;
        Uri newUri = getContentResolver().insert(contentUri, values);
        if (newUri == null) {
            Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Success adding to Cart", Toast.LENGTH_SHORT).show();
        }

        hasAllRequiredValues = true;
    }

    // Calculate the total price based on quantity and checkbox states
    private void updatePrice() {
        int basePrice = 300;

        if (homeShipping.isChecked()) {
            basePrice += 10;
        }

        int totalPrice = basePrice * quantity;
        walkToolPrice.setText("$ " + totalPrice);
    }

    // Increase quantity by 1
    private void increaseQuantity() {
        quantity++;
        displayQuantity();
    }

    // Decrease quantity by 1
    private void decreaseQuantity() {
        // Ensure quantity doesn't go below zero
        if (quantity == 0) {
            Toast.makeText(ChildWheelchairActivity.this, "Can't decrease quantity below zero", Toast.LENGTH_SHORT).show();
        } else {
            quantity--;
            displayQuantity();
        }
    }

    // Display the current quantity
    private void displayQuantity() {
        walkQuantitynumber.setText(String.valueOf(quantity));
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

        Uri contentUri = WalkOrderContract.OrderEntry.CONTENT_URI;

        return new CursorLoader(this, contentUri, projection, null, null, null);
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

            // Retrieve values from the cursor
            String name = cursor.getString(toolname);
            String price = cursor.getString(toolprice);
            String quantity = cursor.getString(toolquantity);
            String homeshipping = cursor.getString(toolhomeshipping);
            String pickup = cursor.getString(toolpickup);

            // Set the retrieved values to the views
            walkToolName.setText(name);
            walkToolPrice.setText(price);
            walkQuantitynumber.setText(quantity);

            if (pickup.equals("Confirmed PickUp")) {
                pickUp.setChecked(true);
            } else {
                pickUp.setChecked(false);
            }

            if (homeshipping.equals("Confirmed home shipping")) {
                homeShipping.setChecked(true);
            } else {
                homeShipping.setChecked(false);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Reset the views
        walkToolName.setText("");
        walkToolPrice.setText("");
        walkQuantitynumber.setText("");
    }
}
