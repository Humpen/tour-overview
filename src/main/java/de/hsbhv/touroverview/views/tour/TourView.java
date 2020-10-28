package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
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
    private MapView mapView;
    private TourGrid tourGrid;
    @Autowired
    public TourView(MapLocationService mapLocationService){
        this.service = mapLocationService;
    }

    private void init() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        if(tourGrid == null) {
            add(tourGrid = new TourGrid(tour));
        } else {
            tourGrid.updateTourGrid(tour);
        }
        if(mapView == null) {
            add(mapView = new MapView(this.service));
        } {
            mapView.updateMapView(service);
        }
    }

    //TODO Variable tourName liefert nicht den erwarteten String. Bsp.: Anstatt von Tour 1 wird Tour%201 übergeben.

    /**
     * Verhalten ist nicht konsistent bei tourName. Anscheinend machen wir es hier falsch. Wir können uns wohl nicht auf den Parameter verlassen.
     * https://vaadin.com/forum/thread/17321464/access-url-parameter-from-route-layout
     * https://stackoverflow.com/questions/60058913/adapting-url-query-parameters-when-navigating-between-views-in-vaadin-flow
     * Da könnte ne Lösung dabei sein, aber ich leg mich für heute schlafen
     *
     * Problem liegt im Leerzeichen von "Tour n" der wandelt das Leerzeichen um. Einzige Lösung die bisher zuverlässig
     * funktioniert ist das Leerzeichen gegen "_" zu ersetzen und für die Query zurück zu konvertieren.
     */
    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String tourId) {
        List<PointOfInterest> pointOfInterestList = null;
        if(tourId != null){
            JSONObject jsonTour = QueryManager.getTourById(tourId);
            if(jsonTour != null) {
                tour = QueryManager.mapJsonToObject(jsonTour, Tour.class, Tour.class.getSimpleName());
            }
            if(tour != null) {
                pointOfInterestList = tour.getPlaceOfInterests();
            }
            if(pointOfInterestList != null) {
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
