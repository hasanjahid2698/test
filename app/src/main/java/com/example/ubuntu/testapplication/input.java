package com.example.ubuntu.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class input extends Activity {

    DatabaseHelper databaseHelper ;

    public EditText dateEditText, categoryEditText, amountEditText;
    public String dateText, categoryText;
    public Double amountText;
    public Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        databaseHelper = new DatabaseHelper(this);
        //finding variable
        dateEditText = findViewById(R.id.dateEditTextId);
        categoryEditText = findViewById(R.id.categoryEditTextId);
        amountEditText = findViewById(R.id.amountEditTextId);
        addButton = findViewById(R.id.AddButtonId);

        AddData();
    }

    public void AddData(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateText = dateEditText.getText().toString();
                categoryText = categoryEditText.getText().toString();
                amountText = Double.parseDouble(amountEditText.getText().toString());

                Boolean inserted = databaseHelper.insertData(dateText,categoryText,amountText);

                if(inserted) Toast.makeText(input.this, "Data is inserted", Toast.LENGTH_SHORT).show();

//                Toast.makeText(input.this, "Data : "+dateText+" "+categoryText+" "+amountText, Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);

                finish();

            }
        });
    }

}
