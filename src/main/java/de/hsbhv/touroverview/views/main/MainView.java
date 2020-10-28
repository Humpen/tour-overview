package de.hsbhv.touroverview.views.main;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import de.hsbhv.touroverview.backend.graphql.QueryManager;
import de.hsbhv.touroverview.views.about.AboutView;
import de.hsbhv.touroverview.views.tour.TourView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@PWA(name = "Tour Overview", shortName = "Tour Overview",  enableInstallPrompt = false)
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@CssImport("./styles/views/main/main-view.css")
public class MainView extends AppLayout implements HasUrlParameter<String> {

    private final Tabs menu;
    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        layout.add(createFeedbackButton());
//        layout.add(new Image("images/user.svg", "Avatar"));
        return layout;
    }

    private Component createFeedbackButton() {
        Button button = new Button("Tour Feedback");
        button.getElement().getStyle().set("margin-left", "auto");
        button.addClickListener(this::showFeedback);
        return button;
    }
    //TODO Bewertung aus dem CMS holen. Scheint anderes Datenmodell zu sein.
    private void showFeedback(ClickEvent clickEvent){
        UI.getCurrent().getPage().executeJs("return window.location.pathname").then(String.class, location -> {
//            location = location.substring(1);
//            location = location.replace("_"," ");
//            JSONObject jsonTour = QueryManager.getTourByName(location);
//            Tour tour = QueryManager.mapJsonToObject(jsonTour, Tour.class, Tour.class.getSimpleName());
            Dialog dialog = new Dialog();
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.add(createCard());
            dialog.open();
        });
    }

    private HorizontalLayout createCard() {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        Icon icon = new Icon(VaadinIcon.STAR);
        Icon icon2 = new Icon(VaadinIcon.STAR);
        Icon icon3 = new Icon(VaadinIcon.STAR);
        Icon icon4 = new Icon(VaadinIcon.STAR);
        Icon icon5 = new Icon(VaadinIcon.STAR);
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span("person.getName()");
        name.addClassName("name");
        Span date = new Span("person.getDate()");
        date.addClassName("date");
        header.add(name, date);

        Span post = new Span("person.getPost()");
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        IronIcon likeIcon = new IronIcon("vaadin", "heart");
        Span likes = new Span("10");
        likes.addClassName("likes");
        IronIcon commentIcon = new IronIcon("vaadin", "comment");
        Span comments = new Span("5");
        comments.addClassName("comments");
        IronIcon shareIcon = new IronIcon("vaadin", "connect");
        Span shares = new Span("1");
        shares.addClassName("shares");

        actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

        description.add(header, post, actions);
        card.add(icon,icon2,icon3,icon4,icon5, description);
        return card;
    }
    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/HS-BHV.png", "Tour Overview logo"));
        logoLayout.add(new H1("Tour Overview"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        JSONObject json = QueryManager.getAllTours();
        JSONObject data  = json.getJSONObject("data");
        JSONArray tourArray = data.getJSONArray("tours");

        RouterLink[] links = new RouterLink[tourArray.length()+1];
//        new RouterLink("Hello World", TourView.class);,


        for(int i = 0; i < tourArray.length(); ++i){
            JSONObject tour = tourArray.getJSONObject(i);
            String tourName= tour.getString("name");
            String tourID = tour.getString("id");
            tourName = tourName.replace("–", "+");
            tourName = tourName.replace( "ö", "oe");
            tourName = tourName.replace( "ü" ,"ue");
            tourName = tourName.replace("ä", "ae");
            tourName = tourName.replace("Ö","OE");
            tourName = tourName.replace("Ü", "UE");
            tourName = tourName.replace("Ä", "AE");
            links[i] = new RouterLink(tour.getString("name"), TourView.class, tourName = tourName.replace(" ","_"));
        }
        links[links.length-1] = new RouterLink("About", AboutView.class);

        return Arrays.stream(links).map(MainView::createTab).toArray(Tab[]::new);
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.add(content);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        updateChrome();
    }

    private void updateChrome() {
        getTabWithCurrentRoute().ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabWithCurrentRoute() {
        String currentRoute = RouteConfiguration.forSessionScope()
                .getUrl(getContent().getClass());
        return menu.getChildren().filter(tab -> hasLink(tab, currentRoute))
                .findFirst().map(Tab.class::cast);
    }

    private boolean hasLink(Component tab, String currentRoute) {
        return tab.getChildren().filter(RouterLink.class::isInstance)
                .map(RouterLink.class::cast).map(RouterLink::getHref)
                .anyMatch(currentRoute::equals);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String o) {
    }
}
