package com.example.noteapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "NoteApp.db";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_notes";

    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_TITLE = "note_title";

    private static final String COLUMN_DESCRIPTION = "note_description";


    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =  "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    void AddNote(String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();

        vals.put(COLUMN_TITLE, title);
        vals.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, vals);

        if (result == -1 ){
            Toast.makeText(context, "Adding the Note failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Adding the Note is successful", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME, cv,"_id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context,"cant update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"successful update", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context,"Failed delete", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Successful delete", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
