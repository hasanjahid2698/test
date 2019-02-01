package com.example.ubuntu.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public ListView allDataListView;
    public EditText dateText;
    public String selectedDate;
    CustomAdapter customAdapter ;
    List <ListCollection> arralistInformation;

    DatabaseHelper databaseHelper ;

    public Button addButton,showButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.AddInfoButtonId);
        showButton = findViewById(R.id.showButtonId);
        dateText = findViewById(R.id.dateTextId);
        allDataListView = findViewById(R.id.viewListView);
        databaseHelper = new DatabaseHelper(this);
        arralistInformation = databaseHelper.listfromdb();
        customAdapter = new CustomAdapter(this,arralistInformation);

        allDataListView.setAdapter( customAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, input.class);
                startActivityForResult(intent,1);
            }

        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate = dateText.getText().toString();
                arralistInformation = databaseHelper.listfromdbondate(selectedDate);
                customAdapter = new CustomAdapter(MainActivity.this,arralistInformation);

                allDataListView.setAdapter( customAdapter);
            }
        });

        allDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               ListCollection listCollection = arralistInformation.get(position);
               String selectedItemDate, selectedItemCategory;
               int selectedItemId;
               Double selectedItemAmount;
               selectedItemDate = listCollection.getDate();
               selectedItemCategory = listCollection.getCategory();
               selectedItemAmount = listCollection.getAmount();
               selectedItemId = listCollection.getId();

               Intent intent = new Intent(MainActivity.this, editOrDelete.class);
               intent.putExtra("date",selectedItemDate);
               intent.putExtra("category",selectedItemCategory);
               intent.putExtra("amount",selectedItemAmount);
               intent.putExtra("id",selectedItemId);
               startActivityForResult(intent, 2);

//                Toast.makeText(MainActivity.this, "Position : "+position+" "+listCollection.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 || requestCode == 2){
            if(resultCode == RESULT_OK){
                    arralistInformation = databaseHelper.listfromdb();
                    customAdapter = new CustomAdapter(this,arralistInformation);
                    allDataListView.setAdapter(customAdapter);
                Toast.makeText(this, "Called", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
