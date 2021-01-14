package de.hsbhv.touroverview.views.about;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hsbhv.touroverview.views.main.MainView;

@Route(value = "", layout = MainView.class)
@PageTitle("Willkommensseite")
@CssImport("./styles/views/about/about-view.css")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        Span span = new Span();
        String html = "Willkommen auf der Übersichtsseite für die AR-Touren APP! Über das Menü links kann eine die gewünschte Tour eingesehen werden.<br>" +
                "Auf dieser Seite können nur Touren Daten angeschaut/gelesen werden. Sie können hier keine Daten zu einer bestehenden Tour hinzufügen. Dies geschieht über das Content Management System.<br>";
        span.getElement().setProperty("innerHTML", html);
        add(span);
    }
}
