package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import de.hsbhv.touroverview.backend.entities.Tour;

public class TourGrid extends HorizontalLayout {

    public TourGrid(Tour tour) {
        init();
    }

    private void init() {
        setSizeFull();
        setSpacing(false);
        setPadding(false);
        Grid<Tour> grid = new Grid<Tour>();

    }
}
