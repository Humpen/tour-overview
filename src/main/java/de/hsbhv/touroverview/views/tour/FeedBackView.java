package de.hsbhv.touroverview.views.tour;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.hsbhv.touroverview.backend.entities.Bewertung;
import de.hsbhv.touroverview.backend.entities.Bewertungen;

public class FeedBackView extends VerticalLayout {
    public FeedBackView(Bewertungen bewertungen) {
        init(bewertungen);
    }

    private void init(Bewertungen bewertungen) {
        getStyle().set("overflow", "auto");
        setWidth("100%");
        setHeight("40%");

        if (bewertungen == null || bewertungen.getBewertungen().size() == 0) {
            add(new Span("Keine Bewertungen vorhanden."));
        } else {
            add(createOverallCard(bewertungen));
            for (Bewertung bewertung : bewertungen.getBewertungen()) {
                add(createCard(bewertung));
            }
        }
    }

    private HorizontalLayout createOverallCard(Bewertungen bewertungen) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);
        description.add(new Span("Gesamtbewertung: "));

        int oneStar = 0;
        int twoStar = 0;
        int threeStar = 0;
        int fourStar = 0;
        int fiveStar = 0;
        float overAllRating = 0;
        for (Bewertung bewertung : bewertungen.getBewertungen()) {
            switch ((int) bewertung.getValue()) {
                case 5:
                    ++fiveStar;
                    break;
                case 4:
                    ++fourStar;
                    break;
                case 3:
                    ++threeStar;
                    break;
                case 2:
                    ++twoStar;
                    break;
                case 1:
                    ++oneStar;
                    break;
                default:
                    break;
            }
        }
        overAllRating = (oneStar + 2 * twoStar + 3 * threeStar + 4 * fourStar + 5 * fiveStar) / bewertungen.getBewertungen().size();
        HorizontalLayout overAllLayout = new HorizontalLayout();
        for (int i = 0; i < overAllRating; ++i) {
            overAllLayout.add(new Icon(VaadinIcon.STAR));
        }

        int valueInInt = (int) overAllRating;
        if (valueInInt / overAllRating != 1) {
            overAllLayout.add(new Icon(VaadinIcon.STAR_HALF_LEFT));
        }
        overAllLayout.add(new Span(String.valueOf(overAllRating)));
        description.add(overAllLayout);

        for (int j = 4; j >= 0; --j) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            for (int i = j; i >= 0; --i) {
                horizontalLayout.add(new Icon(VaadinIcon.STAR));
            }
            switch (j) {
                case 4:
                    horizontalLayout.add(new Span(String.valueOf(oneStar)));
                    break;
                case 3:
                    horizontalLayout.add(new Span(String.valueOf(twoStar)));
                    break;
                case 2:
                    horizontalLayout.add(new Span(String.valueOf(threeStar)));
                    break;
                case 1:
                    horizontalLayout.add(new Span(String.valueOf(fourStar)));
                    break;
                case 0:
                    horizontalLayout.add(new Span(String.valueOf(fiveStar)));
                    break;
                default:
                    break;
            }
            description.add(horizontalLayout);
        }
        card.add(description);
        return card;
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
        for (int i = 1; i <= bewertung.getValue(); ++i) {
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
