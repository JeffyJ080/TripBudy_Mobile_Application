package com.example.tripbudymobileapplication.model;

import java.sql.Date;

public class Memory {
    private final Integer memoryID;
    private final Integer tripID;
    private String caption;
    private String imgPath;
    private final Date datePosted;

    // Constructor class to initialise the Memory object
    public Memory(Integer memoryID, Integer tripID, String caption, String imgPath, Date datePosted){
        this.memoryID = memoryID;
        this.tripID = tripID;
        this.caption = caption;
        this.imgPath = imgPath;
        this.datePosted = datePosted;
    }

    // Getter methods to access the private fields

    public Integer getMemoryID() {
        return memoryID;
    }

    public Integer getTripID() {
        return tripID;
    }

    public String getCaption() {
        return caption;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    // Setter methods to modify the private fields (Specifically image path and caption)

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    // Functions and Methods for this class alone


}
