package dev.lwnd.realestate.ui.view;

import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMap;
import com.flowingcode.vaadin.addons.googlemaps.LatLon;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import dev.lwnd.realestate.Global;
import dev.lwnd.realestate.model.Property;
import dev.lwnd.realestate.service.PropertyService;
import dev.lwnd.realestate.ui.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "property/:category/:propertyId", layout = MainLayout.class)
@PageTitle("Property Details | Real Estate")
public class PropertyDetailView extends Div implements BeforeEnterObserver {

    private final PropertyService propertyService;

    public PropertyDetailView(@Autowired PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String category = event.getRouteParameters().get("category").orElse("unknown");
        Integer propertyId = Integer.valueOf(event.getRouteParameters().get("propertyId").orElse("0"));

        Property property = propertyService.fetchPropertyById(category, propertyId);
        if (property != null) {
            add(buildPropertyDetailsLayout(property));
        } else {
            add(new Div(new Span("Property not found")));
        }
    }

    private Component buildPropertyDetailsLayout(Property property) {
        Div container = new Div();
        container.addClassName("property-details-container");

        H1 name = new H1(property.getName());
        Div nameContainer = new Div(name);
        nameContainer.addClassName("name-container");
        container.add(nameContainer);

        HorizontalLayout mainContent = new HorizontalLayout();
        mainContent.setWidthFull();
        mainContent.addClassName("main-content");

        Div carousel = buildCarousel(property.getImagesUrl());

        VerticalLayout details = new VerticalLayout();
        details.addClassName("details");

        Span price = new Span("Price: €" + property.getPrice());
        Span size = new Span("Size: " + property.getSize() + " sqm");
        details.add(price, size);

        Div map = buildGoogleMap(property.getLocation());

        details.add(map);

        mainContent.add(carousel, details);
        container.add(mainContent);

        return container;
    }

    private Div buildGoogleMap(String location) {
        Div mapContainer = new Div();
        mapContainer.addClassName("map-container");
        if (!location.isEmpty()) {
            String[] coords = location.split(",");
            GoogleMap mapFrame = new GoogleMap(Global.GOOGLE_API_KEY, null, null);
            mapFrame.setMapType(GoogleMap.MapType.SATELLITE);
            mapFrame.setWidth("400px");
            mapFrame.setHeight("300px");
            mapFrame.setCenter(new LatLon(Double.parseDouble(coords[0]), Double.parseDouble(coords[1])));
            mapFrame.addMarker("Property Location",
                    new LatLon(Double.parseDouble(coords[0]), Double.parseDouble(coords[1])),
                    true,
                    "");
            mapContainer.add(mapFrame);
        }
        return mapContainer;
    }

    private Div buildCarousel(List<String> urls) {
        List<Slide> slides = new ArrayList<>();
        for (String url : urls) {
            Image image = new Image(url, "Image");
            image.getElement().setAttribute("draggable", "false");
            image.setWidth("400px");
            image.setHeight("300px");
            Slide slide = new Slide(image);
            slides.add(slide);
        }
        Carousel carousel = new Carousel(slides.toArray(new Slide[0]))
                .withAutoProgress()
                .withSlideDuration(3)
                .withStartPosition(0);

        Div carouselContainer = new Div(carousel);
        carouselContainer.addClassName("carousel-container");
        carouselContainer.setWidth("400px");
        carouselContainer.setHeight("300px");

        return carouselContainer;
    }

}
