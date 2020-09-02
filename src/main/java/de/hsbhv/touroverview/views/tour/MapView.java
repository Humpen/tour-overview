package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import de.hsbhv.touroverview.leaflet.MapComponent;
import de.hsbhv.touroverview.leaflet.MapLocationService;

public class MapView extends HorizontalLayout {

    private MapComponent map;
    private MapLocationService service;
    public MapView(MapLocationService mapLocationService) {
         init(mapLocationService);
    }
    private void init(MapLocationService mapLocationService) {
        setSizeFull();
        setSpacing(false);
        setPadding(false);
        this.service = mapLocationService;
        map = new MapComponent();
        map.addMarkersAndZoom(service.getAll());
        add(map);
    }

    public void updateMapVieww(MapLocationService mapLocationService) {
        if(mapLocationService == null) {
            return;
        }
        this.service = mapLocationService;
        map.removeAllMarker();
        map.addMarkersAndZoom(service.getAll());
    }
}
