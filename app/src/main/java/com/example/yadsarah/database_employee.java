package com.example.yadsarah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database_employee extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="yadSarah1.db";
    public static final String TABLE_NAME ="employee_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="NAME";
    public static final String COL_3 ="WORK";
    public static final String COL_4 ="HSALARY";
    public static final String COL_5 ="PASSWORD";




    public database_employee(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String temp="create table "+TABLE_NAME+" (ID INTERGER PRIMARY KEY ,NAME TEXT,WORK TEXT, HSALARY REAL,PASSWORD TEXT)";
        db.execSQL(temp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    //insert to the datbase
    public boolean insert(String id,String name,String work,String hsalary,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,work);
        contentValues.put(COL_4,hsalary);
        contentValues.put(COL_5,password);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else return true;

    }
    //get all data
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    //edit data
    public boolean edit(String id,String name,String work,String hsalary,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,work);
        contentValues.put(COL_4,hsalary);
        contentValues.put(COL_5,password);
        db.update(TABLE_NAME,contentValues," ID = ? ",new String[] { id });
        return true;

    }
   //delete data
    public Integer delete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] { id });

    }


}
