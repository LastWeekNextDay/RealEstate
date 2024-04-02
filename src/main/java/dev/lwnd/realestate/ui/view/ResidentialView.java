package dev.lwnd.realestate.ui.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import dev.lwnd.realestate.model.Property;
import dev.lwnd.realestate.service.PropertyService;
import dev.lwnd.realestate.ui.MainLayout;
import dev.lwnd.realestate.ui.component.PropertyCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "residential", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PageTitle("Residential Listings | Real Estate")
public class ResidentialView extends Div {

    private final PropertyService propertyService;

    @Autowired
    public ResidentialView(PropertyService propertyService) {
        this.propertyService = propertyService;
        addClassName("residential-view");
        setSizeFull();
        loadProperties();
    }

    private void loadProperties() {
        List<Property> properties = propertyService.fetchResidentialProperties();
        for (Property property : properties) {
            add(new PropertyCard(property, "residential"));
        }
    }
}
