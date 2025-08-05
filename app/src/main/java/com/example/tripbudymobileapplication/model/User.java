package com.example.tripbudymobileapplication.model;

public class User {
    private final Integer userID;
    private String userName;
    private String userEmail;

    // Constructor class to initialise the User object
    public User (Integer ID, String Name, String Email){
        this.userID = ID;
        this.userName = Name;
        this.userEmail = Email;
    }

    // Getter methods to access the private fields

    public Integer getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
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
