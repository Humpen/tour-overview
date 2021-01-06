package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

public class Vuforia {
    public String name;
    @SerializedName("bilder")
    public Image image;
    public String id;
    @SerializedName("tourId")
    public Tour tour;


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public Tour getTourId() {
        return tour;
    }
}
