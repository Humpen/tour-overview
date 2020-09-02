package de.hsbhv.touroverview.leaflet;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Demo backend that accepts up to 100 fishing spots. Data is shared with all
 * users.
 */
@Service
@SessionScope
public class MapLocationService {

    private List<MapLocation> spots = new ArrayList<MapLocation>();


    public List<MapLocation> getAll() {

        return Collections.unmodifiableList(spots);
    }

    public void addSpot(MapLocation spot) {

        // protect concurrent access since MapLocationService is a singleton

            spots.add(spot);

            if (spots.size() > 100) {
                spots.remove(0);
            }
    }
    public void removeAllSpots(){
            spots = new ArrayList<>();
    }
}
