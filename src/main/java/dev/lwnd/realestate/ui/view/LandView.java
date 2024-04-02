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

@Route(value = "land", layout = MainLayout.class)
@PageTitle("Land Listings | Real Estate")
public class LandView extends Div {

    private final PropertyService propertyService;

    @Autowired
    public LandView(PropertyService propertyService) {
        this.propertyService = propertyService;
        addClassName("land-view");
        setSizeFull();
        loadProperties();
    }

    private void loadProperties() {
        List<Property> properties = propertyService.fetchLandProperties();
        for (Property property : properties) {
            add(new PropertyCard(property, "land"));
        }
    }
}
