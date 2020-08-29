package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.hsbhv.touroverview.leaflet.MapComponent;
import de.hsbhv.touroverview.leaflet.MapLocationService;
import de.hsbhv.touroverview.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class TourView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;
    private MapComponent map;
    private MapLocationService service;
    @Autowired
    public TourView(MapLocationService service) {

        this.service = service;
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        map = new MapComponent();
        map.setWidthFull();
        map.addMarkersAndZoom(service.getAll());
        add(map);

//        setId("hello-world-view");
//        name = new TextField("Your name");
//        sayHello = new Button("Say hello");
//        add(name, sayHello);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//        sayHello.addClickListener( e-> {
//            Notification.show("Hello " + name.getValue());
//        });
    }

}
