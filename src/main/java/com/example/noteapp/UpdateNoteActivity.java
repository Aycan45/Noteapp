package com.example.noteapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateNoteActivity extends AppCompatActivity {

    EditText title_input, description_input;
    ImageButton update_button, delete_button;

    String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        title_input = findViewById(R.id.title1);

        description_input = findViewById(R.id.description1);

        update_button = findViewById(R.id.update_btn);

        delete_button = findViewById(R.id.delete_btn);

        //first we call the methods
        getAndSetIntentData();

        // we set the action bar title after the getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper dbhelp = new DatabaseHelper(UpdateNoteActivity.this);
                title = title_input.getText().toString().trim();
                description = description_input.getText().toString().trim();

                //Then we call this
                dbhelp.updateData(id, title, description);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete();
            }
        });

    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("description"))
        {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            //setting intent data
            title_input.setText(title);
            description_input.setText(description);
        }
        else {
            Toast.makeText(this,"No data available",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDelete(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " note?");
        builder.setMessage("Are you sure you want to delete " + title + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper dh = new DatabaseHelper(UpdateNoteActivity.this);
                dh.deleteData(id);
                //getting back to main activity after delete
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //this shows the alert dialog
        builder.create().show();
    }
}