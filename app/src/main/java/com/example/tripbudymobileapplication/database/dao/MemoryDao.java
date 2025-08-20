package com.example.tripbudymobileapplication.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tripbudymobileapplication.database.DatabaseHelper;
import com.example.tripbudymobileapplication.database.model.Memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryDao {
    private static SQLiteDatabase db;

    public MemoryDao(Context context){
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insertMemory(Memory m){
        ContentValues values = new ContentValues();
        values.put("caption", m.getCaption());
        values.put("imgPath", m.getImgPath());
        values.put("mp3Path", m.getMp3Path());
        values.put("datePosted", m.getDatePosted());
        db.insert("memories", null, values);
    }

    public List<Memory> getAllMemories(){
        List<Memory> memories = new ArrayList<>();
        Cursor cursor = db.query("memories", null, null, null, null, null, null);

        while (cursor.moveToNext()){
            String caption = cursor.getString(cursor.getColumnIndexOrThrow("caption"));
            String imgPath = cursor.getString(cursor.getColumnIndexOrThrow("imgPath"));
            String mp3Path = cursor.getString(cursor.getColumnIndexOrThrow("mp3Path"));
            Long datePosted = cursor.getLong(cursor.getColumnIndexOrThrow("datePosted"));

            memories.add(new Memory(caption, imgPath, mp3Path, datePosted));
        }
        cursor.close();

        return memories;
    }
}
