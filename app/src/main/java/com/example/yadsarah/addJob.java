package com.example.yadsarah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addJob extends AppCompatActivity {
    database_job mydb;

    EditText positon,branch,requiremint,jobId;

    Button remove,add,view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_add);

        mydb=new database_job(this);

        positon=(EditText)findViewById(R.id.positionE);
        branch=(EditText)findViewById(R.id.branchE);
        requiremint=(EditText)findViewById(R.id.RequirementE);
        jobId=(EditText)findViewById(R.id.editTextText4);

        add=(Button) findViewById(R.id.Add);
        remove=(Button) findViewById(R.id.button6);
        view=(Button) findViewById(R.id.view);

        Add();
        viewAll();
        remove();

    }

    public void Add(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted= mydb.insert(positon.getText().toString(),branch.getText().toString(),requiremint.getText().toString());
                        if(isInserted=true){
                            Toast.makeText(addJob.this,"job added",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(addJob.this,"error, try again ",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }



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
                    buffer.append(" ID :"+res.getString(0)+"\n");
                    buffer.append(" position :"+res.getString(1)+"\n");
                    buffer.append("branch :"+res.getString(2)+"\n");
                    buffer.append("requiremint :"+res.getString(3)+"\n\n");
                }
                showMessage("data",buffer.toString());
            }
        });
    }

    public void remove() {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(addJob.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete the shift?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer deletedRow = mydb.delete(jobId.getText().toString());

                        if (deletedRow > 0) {
                            Toast.makeText(addJob.this, "Shift deleted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(addJob.this, "Shift not deleted", Toast.LENGTH_LONG).show();
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