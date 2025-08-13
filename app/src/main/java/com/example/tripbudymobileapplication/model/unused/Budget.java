package com.example.tripbudymobileapplication.model.unused;

import java.sql.Date;

public class Budget {
    private final Integer budgetID;
    private final Integer tripID;
    private Double amountAllocated;
    private Double amountSpent;
    private Date lastUpdated;

    // Constructor class to initialise the Budget object
    public Budget (Integer budgetID, Integer tripID, Double amountAllocated, Double amountSpent, Date lastUpdated){
        this.budgetID = budgetID;
        this.tripID = tripID;
        this.amountAllocated = amountAllocated;
        this.amountSpent = amountSpent;
        this.lastUpdated = lastUpdated;
    }

    // Getter methods to access the private fields

    public Integer getBudgetID() {
        return budgetID;
    }

    public Integer getTripID() {
        return tripID;
    }

    public Double getAmountAllocated() {
        return amountAllocated;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    // Setter methods to modify the private fields (Specifically amount spent, last updated, and possibly amount allocated)

    public void setAmountSpent(Double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setAmountAllocated(Double amountAllocated) {
        this.amountAllocated = amountAllocated;
    }

    // Functions and Methods for this class alone


}
