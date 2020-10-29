package de.hsbhv.touroverview.backend.entities;

import java.util.Date;

public class Bewertung {
    public float value;
    public String feedback;
    public Date createdAt;

    public float getValue() {
        return value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getFeedback() {
        return feedback;
    }
}
