package de.hsbhv.touroverview.backend.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Message class for a tour
 */
public class Tour {

    public String name;
    public String id;
    @SerializedName("startpunkt")
    public Location startingPoint;
    @SerializedName("beschreibung")
    public Description description;
    @SerializedName("dauer")
    public int duration;
    @SerializedName("strecke")
    public double routeLength;
    @SerializedName("sehenswuerdigkeiten")
    public List<PointOfInterest> pointOfInterests;

    public List<PointOfInterest> getPlaceOfInterests() {
        return pointOfInterests;
    }

    public void setPlaceOfInterests(List<PointOfInterest> pointOfInterests) {
        this.pointOfInterests = pointOfInterests;
    }


    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
