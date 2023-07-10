package com.example.yadsarah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class workSearch extends AppCompatActivity {

    private ListView listView;
    private database_job mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_search);

        listView = findViewById(R.id.listView1);

        view();
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void view() {
        mydb = new database_job(this);

        Cursor res = mydb.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing found");
            return;
        }

        List<String> dataList = new ArrayList<>();
        while (res.moveToNext()) {
            String rowData =
                    "ID: " + res.getString(0) + "\n" +
                            "Position: " + res.getString(1) + "\n" +
                            "Branch: " + res.getString(2) + " \n" +
                            "Requirements: " + res.getString(3) + "\n\n";
            dataList.add(rowData);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }
}
