package com.example.yadsarah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database_volunteerAssibment extends SQLiteOpenHelper {


    public static final String DATABASE_NAME ="yadSarah5.db";
    public static final String TABLE_NAME ="volunteerAssibment";
    public static final String COL_1 ="ID_volunteer";
    public static final String COL_2 ="date";
    public static final String COL_3 ="startHour";
    public static final String COL_4 ="endHour";

    public static final String COL_5 ="ID";




    public database_volunteerAssibment(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String temp = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ID_volunteer TEXT, date VARCHAR(10), startHour TEXT, endHour TEXT)";
        db.execSQL(temp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }


    // insert the employee shift data to the data base
    public boolean insert(String ID_volunteer,String date,String startHour,String endHour){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,ID_volunteer);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,startHour);
        contentValues.put(COL_4,endHour);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else return true;

    }
    //getdate
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    //delete data from data base
    public Integer delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });

    }



}

