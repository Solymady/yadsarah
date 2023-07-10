package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EmployeeData extends AppCompatActivity {

    database_employee mydb;

    EditText editId,editName,editwork,editHsalary,editPassword;
    Button add,edit,remove,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);

        mydb =new database_employee(this);

        editId=(EditText)findViewById(R.id.EID);
        editName=(EditText)findViewById(R.id.Ename);
        editwork=(EditText)findViewById(R.id.Ework);
        editHsalary=(EditText)findViewById(R.id.EHsalary);
        editPassword=(EditText)findViewById(R.id.Epassword);

        add=(Button) findViewById(R.id.addE);
        edit=(Button) findViewById(R.id.editE);
        remove=(Button) findViewById(R.id.removeE);
        view=(Button) findViewById(R.id.viewAll);

        Add();
        viewAll();
        edit();
        remove();
    }
    //add to the data base
    public void Add(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted= mydb.insert(editId.getText().toString(),editName.getText().toString(),
                                editwork.getText().toString(),editHsalary.getText().toString(),editPassword.getText().toString());
                        if(isInserted=true){
                            Toast.makeText(EmployeeData.this,"employee added",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(EmployeeData.this,"error, try again ",Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    // view data
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
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Position :"+res.getString(2)+"\n");
                    buffer.append("payed for hour :"+res.getString(3)+"\n");
                    buffer.append("password :"+res.getString(4)+"\n\n");
                }
                showMessage("data",buffer.toString());
            }
        });
    }
    //edit data
    public void edit() {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeData.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to update the data?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isUpdated = mydb.edit(
                                editId.getText().toString(),
                                editName.getText().toString(),
                                editwork.getText().toString(),
                                editHsalary.getText().toString(),
                                editPassword.getText().toString());

                        if (isUpdated) {
                            Toast.makeText(EmployeeData.this, "Data is updated", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(EmployeeData.this, "Data is not updated", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User canceled the update operation
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    //remove data
    public void remove() {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeData.this);
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want to delete this employee?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked Yes, proceed with deletion
                                Integer deletedRow = mydb.delete(editId.getText().toString());
                                if (deletedRow > 0) {
                                    Toast.makeText(EmployeeData.this, "Employee deleted", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(EmployeeData.this, "Employee not deleted", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No, do nothing
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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












