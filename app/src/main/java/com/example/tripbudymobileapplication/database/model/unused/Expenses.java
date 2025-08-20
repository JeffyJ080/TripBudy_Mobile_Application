package com.example.tripbudymobileapplication.database.model.unused;

import java.sql.Date;

public class Expenses {
    private final Integer expenseID;
    private final Integer tripID;
    private String title;
    private Double amount;
    private String category;
    private Date date;

    // Constructor class to initialise the Expenses object
    public Expenses(Integer expenseID, Integer tripID, String title, Double amount, String category, Date date){
        this.expenseID = expenseID;
        this.tripID = tripID;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getter methods to access the private fields

    public Integer getExpenseID() {
        return expenseID;
    }

    public Integer getTripID() {
        return tripID;
    }

    public String getTitle() {
        return title;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    // Setter methods to modify the private fields (Specifically title, amount, date, and category)

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Functions and Methods for this class alone


}
