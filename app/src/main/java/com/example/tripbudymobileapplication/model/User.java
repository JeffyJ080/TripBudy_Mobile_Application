package com.example.tripbudymobileapplication.model;

public class User {
    private String userName;
    private String userEmail;
    private Integer totalTrips;

    // Constructor class to initialise the User object
    public User (String Name, String Email, Integer totalTrips){
        this.userName = Name;
        this.userEmail = Email;
        this.totalTrips = totalTrips;
    }

    // Getter methods to access the private fields

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getTotalTrips(){
        return totalTrips;
    }

    // Setter methods to modify the private fields (Specifically username and email)

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Functions and Methods for this class alone


}
