package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class shiftVolunteer extends AppCompatActivity {

    database_sc_volunteer mydb;

    EditText volunteerId,shiftDate,stratH,endH,shiftID;

    Button add,remove,view,selectDate;

    static final int DATE_ID=0;
    int year_y,month_y,day_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_volunteer);

        mydb=new database_sc_volunteer(this);

        volunteerId=(EditText)findViewById(R.id.volunteerIdE);
        shiftDate=(EditText)findViewById(R.id.shiftDateE);
        shiftID=(EditText)findViewById(R.id.shitIDE);
        stratH=(EditText)findViewById(R.id.startH);
        endH=(EditText)findViewById(R.id.endH);

        add=(Button) findViewById(R.id.add);
        remove=(Button) findViewById(R.id.remove);
        view=(Button) findViewById(R.id.view);
        selectDate=(Button) findViewById(R.id.selectDate);


        calenderDate();
        showDialog();

        Add();
        viewAll();
        remove();

    }

    //add shift to database
    public void Add(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted= mydb.insert(volunteerId.getText().toString(),shiftDate.getText().toString(),stratH.getText().toString() ,endH.getText().toString());
                        if(isInserted=true){
                            Toast.makeText(shiftVolunteer.this,"shift added",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(shiftVolunteer.this,"error, try again ",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    //open calender to the user to chose date
    public void showDialog(){
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);

            }
        });
    }

    //set the calender date of the field
    public DatePickerDialog.OnDateSetListener datePick =new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_y=year;
            month_y=month;
            day_y=dayOfMonth;
            shiftDate.setText(day_y+"/"+month_y+"/"+year_y);

        }
    };

    public Dialog onCreateDialog(int id, Bundle args){
        if(id==DATE_ID) {
            return new DatePickerDialog(this, datePick, year_y, month_y, day_y);
        }
        return null;
    }

    //add the date to the field
    public void calenderDate(){
        Calendar cal=Calendar.getInstance();
        year_y=cal.get(Calendar.YEAR);
        month_y=cal.get(Calendar.MONTH);
        day_y=cal.get(Calendar.DAY_OF_MONTH);
    }






    //get the data fro, database and but it in message
    public void viewAll(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= mydb.getAllData();
                if(res.getCount() ==0){
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("shift ID :"+res.getString(0)+"\n");
                    buffer.append(" ID :"+res.getString(1)+"\n");
                    buffer.append("date :"+res.getString(2)+"\n");
                    buffer.append("time :"+res.getString(3)+" to "+res.getString(4)+"\n\n");
                }
                showMessage("data",buffer.toString());
            }
        });
    }

    //remove shift from database
    public void remove() {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(shiftVolunteer.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete the shift?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer deletedRow = mydb.delete(shiftID.getText().toString());

                        if (deletedRow > 0) {
                            Toast.makeText(shiftVolunteer.this, "Shift deleted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(shiftVolunteer.this, "Shift not deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User canceled the delete operation
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}


















