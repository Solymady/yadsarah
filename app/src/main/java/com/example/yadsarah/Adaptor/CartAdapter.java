package com.example.yadsarah.Adaptor;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.yadsarah.Database.WalkOrderContract;
import com.example.yadsarah.R;

public class CartAdapter extends CursorAdapter {

    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.walk_cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView toolName, yesHomeShipping, yesPickUp, toolPrice, toolQuantity;

        toolName = view.findViewById(R.id.walkToolNameinOrderSummary);
        toolPrice = view.findViewById(R.id.walkPriceinOrderSummary);
        yesHomeShipping = view.findViewById(R.id.walkHomeShipping);
        yesPickUp = view.findViewById(R.id.walkPickUp);
        toolQuantity = view.findViewById(R.id.walkQuantityinOrderSummary);

        int toolNameIndex = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_NAME);
        int toolPriceIndex = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_PRICE);
        int toolQuantityIndex = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_QUANTITY);
        int homeShippingIndex = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING);
        int pickUpIndex = cursor.getColumnIndex(WalkOrderContract.OrderEntry.COLUMN_PICKUP);

        String nameOfTool = cursor.getString(toolNameIndex);
        String priceOfTool = cursor.getString(toolPriceIndex);
        String quantityOfTool = cursor.getString(toolQuantityIndex);
        String homeShipping = cursor.getString(homeShippingIndex);
        String pickUp = cursor.getString(pickUpIndex);

        toolName.setText(nameOfTool);
        toolPrice.setText(priceOfTool);
        yesHomeShipping.setText(homeShipping);
        yesPickUp.setText(pickUp);
        toolQuantity.setText(quantityOfTool);
    }
}
