package com.example.tripbudymobileapplication.database.model;

public class Memory {
    private String caption;
    private String imgPath = "memories/uuid.jpg";
    private String mp3Path = "music/uuid.mp3";
    private final Long datePosted;

    // Constructor class to initialise the Memory object
    public Memory(String caption, String imgPath, String mp3Path, Long datePosted){
        this.caption = caption;
        this.imgPath = imgPath;
        this.datePosted = datePosted;
        this.mp3Path = mp3Path;

    }

    // Getter methods to access the private fields

    public String getCaption() {
        return caption;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getMp3Path() {
        return mp3Path;
    }

    public Long getDatePosted() {
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
