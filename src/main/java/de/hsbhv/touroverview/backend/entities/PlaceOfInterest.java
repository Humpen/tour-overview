package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Message class for a place of interest. ("Sehenswürdigkeit")
 */

public class PlaceOfInterest {
    public String tour;
    public String name;
    public Location position;
    public int radius;
    public String infoText;
    @SerializedName("naechsteSehenswurdigkeit")
    public PlaceOfInterest nextPOI;

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public PlaceOfInterest getNextPOI() {
        return nextPOI;
    }

    public void setNextPOI(PlaceOfInterest nextPOI) {
        this.nextPOI = nextPOI;
    }
}
