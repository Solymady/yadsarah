package com.example.yadsarah.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class WalkOrderProvider extends ContentProvider {

    public static final int ORDER = 100;

    public static UriMatcher ysUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        ysUriMatcher.addURI(WalkOrderContract.CONTENT_AUTHORITY, WalkOrderContract.PATH, ORDER);
    }
    public WalkOrderHelper wHelper;

    @Override
    public boolean onCreate() {
        wHelper = new WalkOrderHelper(getContext());
        return true;
    }


    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase yadSarahDatabase = wHelper.getReadableDatabase();
        Cursor cursor;
        int wMatch = ysUriMatcher.match(uri);
        switch (wMatch) {
            case ORDER:
                cursor = yadSarahDatabase.query(WalkOrderContract.OrderEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot make query for URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    public String getType(Uri uri) {
        return null;
    }


    public Uri insert(Uri uri, ContentValues values) {
        int ymatch = ysUriMatcher.match(uri);
        switch (ymatch) {
            case ORDER:
                return insertCart(uri, values);

            default:
                throw new IllegalArgumentException("Cannot insert data for URI: " + uri);
        }
    }

    private Uri insertCart(Uri uri, ContentValues values) {
        String name = values.getAsString(WalkOrderContract.OrderEntry.COLUMN_NAME);
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        String quantity = values.getAsString(WalkOrderContract.OrderEntry.COLUMN_QUANTITY);
        if (quantity == null || quantity.isEmpty()) {
            throw new IllegalArgumentException("Quantity is required");
        }

        String price = values.getAsString(WalkOrderContract.OrderEntry.COLUMN_PRICE);
        if (price == null || price.isEmpty()) {
            throw new IllegalArgumentException("Price is required");
        }

        SQLiteDatabase ysDatabase = wHelper.getWritableDatabase();
        long id = ysDatabase.insert(WalkOrderContract.OrderEntry.TABLE_NAME, null, values);

        if (id == -1) {
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase ysDatabase = wHelper.getWritableDatabase();
        int ymatch = ysUriMatcher.match(uri);
        switch (ymatch) {
            case ORDER:
                rowsDeleted = ysDatabase.delete(WalkOrderContract.OrderEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Cannot delete data for URI: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdated;
        SQLiteDatabase ysDatabase = wHelper.getWritableDatabase();
        int ymatch = ysUriMatcher.match(uri);
        switch (ymatch) {
            case ORDER:
                rowsUpdated = ysDatabase.update(WalkOrderContract.OrderEntry.TABLE_NAME, values, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Cannot update data for URI: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
