package com.example.tripbudymobileapplication.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tripbudymobileapplication.database.DatabaseHelper;

public class UserDao {

    private SQLiteDatabase db;

    public UserDao(Context context){
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insertUser(String username, String email, Integer trips){
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("trips", trips);
        return db.insert("users", null, values);
    }

    public Cursor getAllUsers(){
        return db.query("users", null, null, null, null, null, null);
    }
}
