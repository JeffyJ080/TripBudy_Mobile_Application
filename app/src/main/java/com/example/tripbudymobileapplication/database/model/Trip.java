package com.example.tripbudymobileapplication.database.model;

import java.sql.Date;

public class Trip {
    private Integer tripID;
    private String tripDestination;
    private Date tripStartDate;
    private Date tripEndDate;
    private String tripNotes;
    private Double Expenses;

    // Constructor class to initialise the Trip object
    public Trip (String Destination, Date startDate, Date endDate, String Notes, Double Expenses){
        this.tripDestination = Destination;
        this.tripStartDate = startDate;
        this.tripEndDate = endDate;
        this.tripNotes = Notes;
        this.Expenses = Expenses;
    }

    // Getter methods to access the private fields

    public Integer getTripID() {
        return tripID;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public Date getTripStartDate() {
        return tripStartDate;
    }

    public Date getTripEndDate() {
        return tripEndDate;
    }

    public String getTripNotes() {
        return tripNotes;
    }

    // Setter methods to modify the private fields (Specifically notes , start, end, and destination)

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public void setTripEndDate(Date tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public void setTripStartDate(Date tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public void setTripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
    }

    // Functions and methods to this class alone
    public static void saveTrip(Trip t){
        // TODO: Save trip to database

    }

}
