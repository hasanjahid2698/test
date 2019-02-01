package com.example.ubuntu.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editOrDelete extends Activity{

    public EditText dateEditingEditText,categoryEditingEditText,amountEditingEditText;
    public Button backButton, editButton, deleteButton;
    public String dateText, categoryText;
    public int idText;
    public double amountText;
    public DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete);


        // finding
        dateEditingEditText = findViewById(R.id.dateEditingEditTextId);
        categoryEditingEditText = findViewById(R.id.categoryEditingEditTextId);
        amountEditingEditText = findViewById(R.id.amountEditingEditTextId);
        backButton = findViewById(R.id.backButtonId);
        editButton = findViewById(R.id.editButtonId);
        deleteButton = findViewById(R.id.DeleteButtonId);
        databaseHelper = new DatabaseHelper( this);

        Bundle bundle = getIntent().getExtras();
        idText=bundle.getInt("id");
        dateText = bundle.getString("date");
        categoryText = bundle.getString("category");
        amountText = bundle.getDouble("amount");

        dateEditingEditText.setText(dateText);
        categoryEditingEditText.setText(categoryText);
        amountEditingEditText.setText(Double.toString(amountText));



        // Back Button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //edit Button
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id =idText;
                String selectedDate = dateEditingEditText.getText().toString();
                String selectedCategory = categoryEditingEditText.getText().toString();
                Double amount = Double.parseDouble(amountEditingEditText.getText().toString());
                Integer updated = databaseHelper.updateData(id,selectedDate,selectedCategory,amount);
                Toast.makeText(editOrDelete.this, updated+" row is updated", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);

                finish();

            }
        });

        //delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = idText;
                Integer deleted = databaseHelper.deleteData(id);
                Toast.makeText(editOrDelete.this, deleted+" row is deleted", Toast.LENGTH_SHORT).show();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);

                finish();
            }
        });

    }
}
