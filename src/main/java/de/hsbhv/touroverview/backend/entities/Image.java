package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Image {
    @SerializedName("url")
    public String imageUrl;
    public File image;
    public String width;
    public String height;


    public String getImageUrl() {
        return imageUrl;
    }

    public File getImage() {
        return image;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
