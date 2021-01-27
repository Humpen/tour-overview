package de.hsbhv.touroverview.views.about;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.hsbhv.touroverview.backend.entities.VuforiaCallBack;
import de.hsbhv.touroverview.backend.vuforia.AsyncCaller;
import de.hsbhv.touroverview.backend.vuforia.VuforiaInfo;
import de.hsbhv.touroverview.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainView.class)
@PageTitle("Willkommensseite")
@CssImport("./styles/views/about/about-view.css")
public class AboutView extends Div {
    @Autowired
    private AsyncCaller asyncCaller;
    private Button button;

    public AboutView() {
        setId("about-view");
        Span span = new Span();
        String html = "Willkommen auf der Übersichtsseite für die AR-Touren APP! Über das Menü links kann eine die gewünschte Tour eingesehen werden.<br>" +
                "Auf dieser Seite können nur Touren Daten angeschaut/gelesen werden. Sie können hier keine Daten zu einer bestehenden Tour hinzufügen. Dies geschieht über das Content Management System.<br>";
        span.getElement().setProperty("innerHTML", html);
        add(span);
        add(new H1("Vuforia Target Übersicht"));
        add(new Span("Aktualisiert nur alle 24 Stunden!"));
        Grid<VuforiaCallBack> grid = new Grid<>();
        grid.setItems(VuforiaInfo.getInstance().getCollection());
        grid.addColumn(vuforiaCallBack -> vuforiaCallBack.imageName).setHeader("Bild Name");
        grid.addColumn(vuforiaCallBack -> vuforiaCallBack.responseCode).setHeader("Code");
        grid.addColumn(vuforiaCallBack -> vuforiaCallBack.result).setHeader("Status");
        add(grid);
        button = new Button("Update Targets");
        button.addClickListener(buttonClickEvent -> {
            button.setEnabled(false);
            Dialog dialog = new Dialog();
            dialog.add("Das Update kann bis zu 15 Minuten dauern!");
            dialog.open();
            asyncCaller.updateVuforiaTargets();
        });
        add(button);
    }
}