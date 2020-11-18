package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.hsbhv.touroverview.backend.entities.Bewertungen;
import de.hsbhv.touroverview.backend.entities.Location;
import de.hsbhv.touroverview.backend.entities.PointOfInterest;
import de.hsbhv.touroverview.backend.entities.Tour;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import de.hsbhv.touroverview.leaflet.MapLocation;
import de.hsbhv.touroverview.leaflet.MapLocationService;
import de.hsbhv.touroverview.views.main.MainView;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Route(value = "hello", layout = MainView.class)
@Route(value = "", layout = MainView.class)
@PageTitle("Tour View")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class TourView extends HorizontalLayout implements HasUrlParameter<String> {

    private MapLocationService service;
    private Tour tour;
    private Bewertungen bewertungen;
    private MapView mapView;
    private TourGrid tourGrid;
    private VerticalLayout verticalLayout;
    @Autowired
    public TourView(MapLocationService mapLocationService){
        this.service = mapLocationService;
    }

    private void init() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        if (verticalLayout == null) {
            verticalLayout = new VerticalLayout();
        }
        if (tourGrid == null) {
            verticalLayout.add(tourGrid = new TourGrid(tour));
            verticalLayout.add(new FeedBackView(bewertungen));
            add(verticalLayout);
        } else {
            tourGrid.updateTourGrid(tour);
            verticalLayout.removeAll();
            verticalLayout.add(tourGrid, new FeedBackView(bewertungen));
        }
        if (mapView == null) {
            add(mapView = new MapView(this.service));
        } {
            mapView.updateMapView(service);
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String tourId) {
        List<PointOfInterest> pointOfInterestList = null;
        if(tourId != null) {
            JSONObject jsonTour = QueryManager.getTourById(tourId);
            if (jsonTour != null) {
                tour = QueryManager.mapJsonToObject(jsonTour, Tour.class, Tour.class.getSimpleName());
            }
            JSONObject jsonBewertungen = QueryManager.getFeedbackById(tourId);
            if (jsonBewertungen != null) {
                bewertungen = QueryManager.mapJsonToObject(jsonBewertungen, Bewertungen.class);
            }
            if (tour != null) {
                pointOfInterestList = tour.getPlaceOfInterests();
            }
            if (pointOfInterestList != null) {
                service.removeAllSpots();
                for (PointOfInterest poi : pointOfInterestList) {
                    Location location = poi.getPosition();
                    service.addSpot(new MapLocation(location.getLatitude(), location.longitude, poi.getName()));
                }
            }
        }
    init();
    }
}
