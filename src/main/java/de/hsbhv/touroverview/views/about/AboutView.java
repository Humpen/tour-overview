package de.hsbhv.touroverview.views.about;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hsbhv.touroverview.views.main.MainView;

@Route(value = "", layout = MainView.class)
@PageTitle("Willkommensseite")
@CssImport("./styles/views/about/about-view.css")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        add(new Label("Willkommen auf der Übersichtsseite für die AR-Touren APP! Über das Menü links kann eine die gewünschte Tour eingesehen werden."));


    }

}
