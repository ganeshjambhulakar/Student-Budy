package com.example.studentbudy;

public class FeatureModel
{
    String title,description;
     int id;
    byte[] image;
    public FeatureModel(int id, String title, String description, byte[] image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public byte[] getImage() {
        return image;
    }
}
