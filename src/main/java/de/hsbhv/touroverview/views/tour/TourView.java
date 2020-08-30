package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import de.hsbhv.touroverview.leaflet.MapComponent;
import de.hsbhv.touroverview.leaflet.MapLocation;
import de.hsbhv.touroverview.leaflet.MapLocationService;
import de.hsbhv.touroverview.views.main.MainView;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;


@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Tour View")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class TourView extends HorizontalLayout implements HasUrlParameter<String> {
    private String tourName;
    private TextField name;
    private Button sayHello;
    private MapComponent map;
    private MapLocationService service;
    @Autowired
    public TourView(MapLocationService service) throws MalformedURLException {

        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        HorizontalLayout horizontalLayout2 = new HorizontalLayout();
//        Grid grid = new Grid();
//        grid.setSizeFull();
//        horizontalLayout1.add(grid);
        Button button = new Button();
        button.addAttachListener(e -> map.addMarker(new MapLocation(53.53806892532847,8.57793047581791,"Weserstrandbad")));
        horizontalLayout1.add(button);
        this.service = service;
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        horizontalLayout1.setSpacing(false);
        horizontalLayout1.setPadding(false);
        horizontalLayout1.setSizeFull();
        horizontalLayout2.setSpacing(false);
        horizontalLayout2.setPadding(false);
        horizontalLayout2.setSizeFull();
        map = new MapComponent();
        map.addMarkersAndZoom(service.getAll());
        horizontalLayout2.add(map);
        add(horizontalLayout1, horizontalLayout2);
//        add(map);
/*        try {
            new TourQuery().getTourByName("null");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        QueryManager.getAllTours();
        JSONObject json = QueryManager.getAllToursAllDetails();
        System.out.println(json);
        if(tourName != null){
            JSONObject tour = QueryManager.getTourByName(tourName);
            tour.get("data");
        }
//        setId("hello-world-view");
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        add(name, sayHello);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//        sayHello.addClickListener( e-> {
//            Notification.show("Hello " + name.getValue());
//        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String tourName) {
        this.tourName = tourName;
        if(tourName != null){
            JSONObject tour = QueryManager.getTourByName(tourName);
            if(tour!= null){
                Notification.show("löppt");
                tour.get("data");
            }
        }
    }
}
