package com.example.yadsarah;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.yadsarah.Database.WalkOrderContract;

public class WalkInfoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageView walkImageView;
    ImageButton walkPlusQuantity, walkMinusQuantity;
    TextView walkQuantityNumber, walkToolName, walkToolPrice;
    CheckBox walkHomeShipping, walkPickUp;
    Button walkAddToCart;
    int walkQuantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredVal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_info);

        // Initialize views
        walkImageView = findViewById(R.id.walkViewInfoImg);
        walkPlusQuantity = findViewById(R.id.walkAddquantity);
        walkMinusQuantity = findViewById(R.id.walkSubquantity);
        walkQuantityNumber = findViewById(R.id.walkQuantity);
        walkToolName = findViewById(R.id.walkToolNameinInfo);
        walkToolPrice = findViewById(R.id.walkToolPrice);
        walkHomeShipping = findViewById(R.id.walkHomeShipping);
        walkPickUp = findViewById(R.id.walkPickUp);
        walkAddToCart = findViewById(R.id.walkAddtocart);

        // Set initial values and listeners
        walkToolName.setText("Tilt Back Wheelchair");
        walkQuantity = 0;
        displayQuantity();

        walkAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save cart information
                boolean saved = walkSaveCart();

                if (saved) {
                    // Start SummaryActivity
                    Intent walkIntent = new Intent(WalkInfoActivity.this, SummaryActivity.class);
                    startActivity(walkIntent);
                }
            }
        });

        walkPlusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increase quantity and update price
                walkQuantity++;
                displayQuantity();
                int updatedPrice = calculatePrice();
                walkToolPrice.setText("$" + updatedPrice);
            }
        });

        walkMinusQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decrease quantity and update price
                if (walkQuantity > 0) {
                    walkQuantity--;
                    displayQuantity();
                    int updatedPrice = calculatePrice();
                    walkToolPrice.setText("$" + updatedPrice);
                } else {
                    Toast.makeText(WalkInfoActivity.this, "Can't decrease below zero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        walkHomeShipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int updatedPrice = calculatePrice();
                walkToolPrice.setText("$" + updatedPrice);
            }
        });

        walkPickUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int updatedPrice = calculatePrice();
                walkToolPrice.setText("$" + updatedPrice);
            }
        });
    }

    // Save cart information to the database
    private boolean walkSaveCart() {
        String toolname = walkToolName.getText().toString();
        String toolprice = walkToolPrice.getText().toString();
        String toolquantity = walkQuantityNumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(WalkOrderContract.OrderEntry.COLUMN_NAME, toolname);
        values.put(WalkOrderContract.OrderEntry.COLUMN_PRICE, toolprice);
        values.put(WalkOrderContract.OrderEntry.COLUMN_QUANTITY, toolquantity);

        // Set home shipping status
        if (walkHomeShipping.isChecked()) {
            values.put(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING, "Confirmed home shipping");
        } else {
            values.put(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING, "Not confirmed home shipping");
        }
        // Set pick up status
        if (walkPickUp.isChecked()) {
            values.put(WalkOrderContract.OrderEntry.COLUMN_PICKUP, "Confirmed PickUp");
        } else {
            values.put(WalkOrderContract.OrderEntry.COLUMN_PICKUP, "Not confirmed PickUp");
        }

        if (mCurrentCartUri == null) {
            // Insert new cart entry
            Uri newUri = getContentResolver().insert(WalkOrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Toast.makeText(this, "Success adding to Cart", Toast.LENGTH_SHORT).show();
                return true;
            }
        } else {
            // Update existing cart entry
            int rowsAffected = getContentResolver().update(mCurrentCartUri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, "Failed to update Cart", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Toast.makeText(this, "Success updating Cart", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
    }

    // Calculate price based on quantity and checkboxes
    private int calculatePrice() {
        int walkToolBasePrice = 220;

        if (walkHomeShipping.isChecked()) {
            walkToolBasePrice += 10;
        }

        return walkToolBasePrice * walkQuantity;
    }

    // Display the current quantity
    private void displayQuantity() {
        walkQuantityNumber.setText(String.valueOf(walkQuantity));
    }

    // Loader methods for loading data from the database
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

        return new CursorLoader(this, mCurrentCartUri, projection, null, null, null);
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

            walkToolName.setText(nameoftool);
            walkToolPrice.setText(priceoftool);
            walkQuantityNumber.setText(quantityoftool);

            if (homeshipping.equals("Confirmed home shipping")) {
                walkHomeShipping.setChecked(true);
            } else {
                walkHomeShipping.setChecked(false);
            }

            if (pickup.equals("Confirmed PickUp")) {
                walkPickUp.setChecked(true);
            } else {
                walkPickUp.setChecked(false);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        walkToolName.setText("");
        walkToolPrice.setText("");
        walkQuantityNumber.setText("");
        walkHomeShipping.setChecked(false);
        walkPickUp.setChecked(false);
    }
}
