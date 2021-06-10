package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddNoteActivity extends AppCompatActivity {

    EditText title_input, description;
    ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title_input = findViewById(R.id.title);
        description = findViewById(R.id.description);
        add = findViewById(R.id.add_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDb = new DatabaseHelper(AddNoteActivity.this);

                myDb.AddNote(title_input.getText().toString().trim(), description.getText().toString().trim());

            }
        });
    }
}