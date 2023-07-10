package com.example.yadsarah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class viewEmplooyeAssbmint extends AppCompatActivity {

    private ListView listView;
    private database_employeeAssibment mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emplooye_assbmint);


        listView = findViewById(R.id.listView);

        view();


    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void view(){

        mydb = new database_employeeAssibment(this);

        StringBuffer buffer=new StringBuffer();
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }
        List<String> dataList = new ArrayList<>();
        while (res.moveToNext()){
            String rowData =
                    "volunteer ID: " + res.getString(1) + "\n" +
                            "date: " + res.getString(2) + "\n" +
                            "time: " + res.getString(3) + " to " + res.getString(4) + "\n\n";
            dataList.add(rowData);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

    }
}
