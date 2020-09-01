package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import de.hsbhv.touroverview.backend.entities.Location;
import de.hsbhv.touroverview.backend.entities.PointOfInterest;
import de.hsbhv.touroverview.backend.entities.Tour;
import de.hsbhv.touroverview.backend.entities.ToursData;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import de.hsbhv.touroverview.leaflet.MapLocation;
import de.hsbhv.touroverview.leaflet.MapLocationService;
import de.hsbhv.touroverview.views.main.MainView;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Tour View")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class TourView extends HorizontalLayout implements HasUrlParameter<String> {

    private MapLocationService service;
    private Tour tour;
    @Autowired
    public TourView(MapLocationService mapLocationService){
        this.service = mapLocationService;
    }

    private void init() {
        removeAll();
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(new TourGrid(tour));
        add(new MapView(this.service));
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
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String tourName) {
        tourName = tourName.replace("_", " ");
        List<PointOfInterest> pointOfInterestList = null;
        if(tourName != null){
            JSONObject jsonTour = QueryManager.getTourByName(tourName);
            tour = QueryManager.mapJsonToObject(jsonTour, Tour.class, Tour.class.getSimpleName());
            if(tour != null) {
                pointOfInterestList = tour.getPlaceOfInterests();
            }
            if(pointOfInterestList != null) {
                for (PointOfInterest poi : pointOfInterestList) {
                    Location location = poi.getPosition();
                    service.addSpot(new MapLocation(location.getLatitude(), location.longitude, poi.getName()));
                }
            }
            JSONObject tours = QueryManager.getAllTours();
            ToursData toursData = QueryManager.mapJsonToObject(tours, ToursData.class);
//            if(jsonTour!= null){
//                Notification.show("löppt");
//                System.out.println(jsonTour.get("data"));
//            }
        }
    init();
    }
}
