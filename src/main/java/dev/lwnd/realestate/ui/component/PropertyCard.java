package dev.lwnd.realestate.ui.component;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouteParam;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import dev.lwnd.realestate.model.Property;
import dev.lwnd.realestate.ui.view.PropertyDetailView;

public class PropertyCard extends Div {

    public PropertyCard(Property property, String category) {
        addClassName("property-card");

        RouterLink link = new RouterLink();
        RouteParam categoryParam = new RouteParam("category", category);
        RouteParam propertyIdParam = new RouteParam("propertyId", String.valueOf(property.getId()));
        RouteParameters parameters = new RouteParameters(categoryParam, propertyIdParam);
        link.setRoute(PropertyDetailView.class, parameters);
        link.addClassName("property-link");

        HorizontalLayout mainHorizontalLayout = new HorizontalLayout();
        mainHorizontalLayout.setSizeUndefined();

        Image image = new Image(property.getImagesUrl().get(0), "Property Image");
        image.setWidth("200px");

        VerticalLayout miniDetails = new VerticalLayout();

        Span name = new Span(property.getName());
        Span price = new Span("Price: €" + property.getPrice());
        Span size = new Span("Size: " + property.getSize() + " sqm");

        miniDetails.add(name, price, size);

        mainHorizontalLayout.add(image, miniDetails);

        link.add(mainHorizontalLayout);

        add(link);
    }
}
