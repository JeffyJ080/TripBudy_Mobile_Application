package com.example.tripbudymobileapplication.database.model;

public class Trip {
    private Integer tripID;
    private String tripDestination;
    private Long tripStartDate;
    private Long tripEndDate;
    private String tripNotes;
    private Double Expenses;

    // Constructor class to initialise the Trip object
    public Trip (String Destination, Long startDate, Long endDate, String Notes, Double Expenses){
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

    public Long getTripStartDate() {
        return tripStartDate;
    }

    public Long getTripEndDate() {
        return tripEndDate;
    }

    public String getTripNotes() {
        return tripNotes;
    }

    public Double getTripExpenses() {
        return Expenses;
    }

    // Setter methods to modify the private fields (Specifically notes , start, end, and destination)

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public void setTripEndDate(Long tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public void setTripStartDate(Long tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public void setTripNotes(String tripNotes) {
        this.tripNotes = tripNotes;
    }
}
