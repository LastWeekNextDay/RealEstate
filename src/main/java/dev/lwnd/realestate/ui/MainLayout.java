package dev.lwnd.realestate.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;
import dev.lwnd.realestate.ui.view.CommercialView;
import dev.lwnd.realestate.ui.view.LandView;
import dev.lwnd.realestate.ui.view.ResidentialView;

@CssImport("./styles/style.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("Real Estate Listings");
        logo.addClassNames("text-l", "m-m");

        Tab residentialTab = new Tab(new RouterLink("Residential", ResidentialView.class));
        Tab commercialTab = new Tab(new RouterLink("Commercial", CommercialView.class));
        Tab landTab = new Tab(new RouterLink("Land", LandView.class));

        Tabs tabs = new Tabs(residentialTab, commercialTab, landTab);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.addThemeVariants(TabsVariant.LUMO_CENTERED);

        HorizontalLayout header = new HorizontalLayout(logo, tabs);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }
}
