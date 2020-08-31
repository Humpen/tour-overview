package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Wrapper class for a list of tour objects.
 * Otherwise GSON can't map it to a java object.
 */
public class ToursData {

    @SerializedName("tours")
    public List<Tour> toursList;

    public List<Tour> getToursList() {
        return toursList;
    }

    public void setToursList(List<Tour> toursList) {
        this.toursList = toursList;
    }
}
