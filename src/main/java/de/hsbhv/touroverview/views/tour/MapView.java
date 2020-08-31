package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import de.hsbhv.touroverview.leaflet.MapComponent;
import de.hsbhv.touroverview.leaflet.MapLocationService;

public class MapView extends HorizontalLayout {

    public MapView(MapLocationService mapLocationService) {
         init(mapLocationService);
    }
    private void init(MapLocationService mapLocationService) {
        setSizeFull();
        setSpacing(false);
        setPadding(false);
        MapComponent map = new MapComponent();
        map.addMarkersAndZoom(mapLocationService.getAll());
        add(map);
    }
}
