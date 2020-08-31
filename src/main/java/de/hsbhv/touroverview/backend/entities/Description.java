package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("html")
    public String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
