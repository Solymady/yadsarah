package com.example.yadsarah.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WalkOrderHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "yadSarah.db";

    public WalkOrderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + WalkOrderContract.OrderEntry.TABLE_NAME + " ("
                + WalkOrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WalkOrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + WalkOrderContract.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, "
                + WalkOrderContract.OrderEntry.COLUMN_PRICE + " TEXT NOT NULL, "
                + WalkOrderContract.OrderEntry.COLUMN_HOME_SHIPPING + " TEXT NOT NULL, "
                + WalkOrderContract.OrderEntry.COLUMN_PICKUP + " TEXT NOT NULL);";

        db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WalkOrderContract.OrderEntry.TABLE_NAME);
        onCreate(db);
    }
}

