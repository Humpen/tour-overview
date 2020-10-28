package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

public class InfoText {
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("text")
    public String description;
}
