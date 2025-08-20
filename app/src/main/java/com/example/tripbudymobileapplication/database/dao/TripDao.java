package com.example.tripbudymobileapplication.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tripbudymobileapplication.database.DatabaseHelper;
import com.example.tripbudymobileapplication.database.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripDao {
    private static SQLiteDatabase db;

    public TripDao(Context context){
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public static Trip insertTrip(Trip t){
        ContentValues values = new ContentValues();
        values.put("destination", t.getTripDestination());
        values.put("startdate", t.getTripStartDate());
        values.put("enddate", t.getTripEndDate());
        values.put("note", t.getTripNotes());
        values.put("expenses", t.getTripExpenses());
        db.insert("trips", null, values);

        return t;
    }

    public static List<Trip> getAllTrips(){
        List<Trip> trips = new ArrayList<>();
        Cursor cursor = db.query("trips", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String destination = cursor.getString(cursor.getColumnIndexOrThrow("destination"));
            Long startdate = cursor.getLong(cursor.getColumnIndexOrThrow("startdate"));
            Long enddate = cursor.getLong(cursor.getColumnIndexOrThrow("enddate"));
            String notes = cursor.getString(cursor.getColumnIndexOrThrow("note"));
            Double expences = cursor.getDouble(cursor.getColumnIndexOrThrow("expenses"));

            trips.add(new Trip(destination, startdate, enddate, notes, expences));
        }
        cursor.close();

        return trips;
    }
}
