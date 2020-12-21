package de.hsbhv.touroverview.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")


@PageTitle("Login | Tour Overview HS-Bremerhaven")

public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();


    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);


        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        Image image = new Image("images/HS-BHV.png", "Tour Overview logo");
        image.setHeight("300 px");
        image.setWidth("300px");
        add(image);
        add(new H1("Tour Overview HS-Bremerhaven"), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()


                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}