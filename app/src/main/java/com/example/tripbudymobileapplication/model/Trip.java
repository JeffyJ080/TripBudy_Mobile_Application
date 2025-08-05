package com.example.tripbudymobileapplication.model;

import java.sql.Date;

public class Trip {
    private final Integer tripID;
    private String tripDestination;
    private Date tripStartDate;
    private Date tripEndDate;
    private String tripNotes;
    private final Integer userID;

    // Constructor class to initialise the Trip object
    public Trip (Integer tripID, String Destination, Date startDate, Date endDate, String Notes, Integer userID){
        this.tripID = tripID;
        this.tripDestination = Destination;
        this.tripStartDate = startDate;
        this.tripEndDate = endDate;
        this.tripNotes = Notes;
        this.userID = userID;
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

    public Integer getUserID() {
        return userID;
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


}
