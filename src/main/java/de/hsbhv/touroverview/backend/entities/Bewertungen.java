package de.hsbhv.touroverview.backend.entities;

public class Bewertungen {
    public float value;
    public Tour tour;
    public String feedback;

    public float getValue() {
        return value;
    }

    public Tour getTour() {
        return tour;
    }

    public String getFeedback() {
        return feedback;
    }
}
