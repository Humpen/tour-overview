package de.hsbhv.touroverview.backend.entities;

/**
 * Message class for a location
 */
public class Location {
    public long distance;
    public long latitude;
    public long longitude;

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

}
