package dev.lwnd.realestate.ui.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dev.lwnd.realestate.model.Property;
import dev.lwnd.realestate.service.PropertyService;
import dev.lwnd.realestate.ui.MainLayout;
import dev.lwnd.realestate.ui.component.PropertyCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "commercial", layout = MainLayout.class)
@PageTitle("Commercial Listings | Real Estate")
public class CommercialView extends Div {

    private final PropertyService propertyService;

    @Autowired
    public CommercialView(PropertyService propertyService) {
        this.propertyService = propertyService;
        addClassName("commercial-view");
        setSizeFull();
        loadProperties();
    }

    private void loadProperties() {
        List<Property> properties = propertyService.fetchCommercialProperties();
        for (Property property : properties) {
            add(new PropertyCard(property, "commercial"));
        }
    }
}