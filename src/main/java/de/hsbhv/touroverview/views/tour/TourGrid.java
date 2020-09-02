package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import de.hsbhv.touroverview.backend.entities.PointOfInterest;
import de.hsbhv.touroverview.backend.entities.Tour;

import java.util.Collection;

public class TourGrid extends HorizontalLayout {
    private Tour tour;
    private Collection<PointOfInterest> poi;
    private Grid<PointOfInterest> grid;
    public TourGrid(Tour tour) {
        this.tour = tour;
        init();
    }

    private void init() {
        setSizeFull();


        if(tour == null) {
            return;
        }
        createGrid();
    }

    private void createGrid() {
        this.poi = tour.getPlaceOfInterests();
        grid = new Grid<>();
        grid.addColumn(PointOfInterest::getName).setHeader("Name");
        grid.addColumn(PointOfInterest::getInfoText).setHeader("Info Text");
        grid.addColumn(PointOfInterest::getRadius).setHeader("Anzeige Radius");
        grid.addColumn(PointOfInterest::getLatitude).setHeader("Latitude");
        grid.addColumn(PointOfInterest::getLongitude).setHeader("Longitude");
        grid.setSizeFull();
        grid.setItems(poi);
        add(grid);
    }

    public void updateTourGrid(Tour tour) {
        if(tour == null) {
            return;
        }
        this.tour = tour;
        this.poi = tour.getPlaceOfInterests();
        grid.setItems(poi);
    }
}
