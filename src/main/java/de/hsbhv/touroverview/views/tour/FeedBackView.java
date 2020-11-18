package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.hsbhv.touroverview.backend.entities.Bewertung;
import de.hsbhv.touroverview.backend.entities.Bewertungen;

public class FeedBackView extends VerticalLayout {
    private VerticalLayout verticalLayout;

    public FeedBackView(Bewertungen bewertungen) {
        init(bewertungen);
    }

    private void init(Bewertungen bewertungen) {
        verticalLayout = new VerticalLayout();
        if (bewertungen == null || bewertungen.getBewertungen().size() == 0) {
            verticalLayout.add(new Span("Keine Bewertungen vorhanden."));
        } else {
            for (Bewertung bewertung : bewertungen.getBewertungen()) {
                verticalLayout.add(createCard(bewertung));
            }
        }
        add(verticalLayout);
    }

    private HorizontalLayout createCard(Bewertung bewertung) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");
        Span date = new Span(bewertung.getCreatedAt().toString());
        date.addClassName("date");
        header.add(date);

        Span post = new Span(bewertung.getFeedback());
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        description.add(header, post, actions);
        for (int i = 1; i < bewertung.getValue(); ++i) {
            card.add(new Icon(VaadinIcon.STAR));
        }
        int valueInInt = (int) bewertung.getValue();
        if (valueInInt / bewertung.getValue() != 1) {
            card.add(new Icon(VaadinIcon.STAR_HALF_LEFT));
        }
        card.add(description);
        return card;
    }
}
