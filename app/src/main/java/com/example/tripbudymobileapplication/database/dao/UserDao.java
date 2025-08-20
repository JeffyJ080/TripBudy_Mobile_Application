package com.example.tripbudymobileapplication.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tripbudymobileapplication.database.DatabaseHelper;
import com.example.tripbudymobileapplication.database.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private SQLiteDatabase db;

    public UserDao(Context context){
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public User insertUser(User u){
        ContentValues values = new ContentValues();
        values.put("username", u.getUserName());
        values.put("email", u.getUserEmail());
        values.put("trips", u.getTotalTrips());
        db.insert("users", null, values);

        return u;
    }

    public List<User> getALlUsers(){
        List<User> users = new ArrayList<>();
        Cursor cursor = db.query("users", null, null, null, null, null, null);

        while (cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            Integer trips = cursor.getInt(cursor.getColumnIndexOrThrow("trips"));

            users.add(new User(username, email, trips));
        }
        cursor.close();

        return users;
    }
}
