package com.example.yadsarah;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database_job extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="yadSarah6.db";
    public static final String TABLE_NAME ="job_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="position";
    public static final String COL_3 ="Branch";
    public static final String COL_4 ="requirement";


    public database_job(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String temp="create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,position TEXT,Branch TEXT, requirement TEXT)";
        db.execSQL(temp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    // insert the employee shift data to the data base
    public boolean insert(String position,String Branch,String requirement){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,position);
        contentValues.put(COL_3,Branch);
        contentValues.put(COL_4,requirement);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }



    public Integer delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });

    }




}
