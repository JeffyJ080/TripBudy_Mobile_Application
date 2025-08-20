package com.example.tripbudymobileapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tripbuddy_db";
    private static final int DATABASE_VERSION = 1;

    // Singleton instance to avoid multiple open connections
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, email TEXT UNIQUE, trips INTEGER DEFAULT 0)");
        db.execSQL("CREATE TABLE memories (id INTEGER PRIMARY KEY AUTOINCREMENT, caption TEXT, imgPath TEXT, mp3Path TEXT, datePosted INTEGER)");
        db.execSQL("CREATE TABLE trips (id INTEGER PRIMARY KEY AUTOINCREMENT, destination TEXT NOT NULL, startdate INTEGER, enddate INTEGER, note TEXT, expenses REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Simple upgrade: drop tables and recreate
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS memories");
        db.execSQL("DROP TABLE IF EXISTS trips");
        onCreate(db);
    }
}
