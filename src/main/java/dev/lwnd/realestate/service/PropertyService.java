package dev.lwnd.realestate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lwnd.realestate.model.Property;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PropertyService {

    private static final String RESIDENTIAL_JSON_PATH = "data/residential.json";
    private static final String COMMERCIAL_JSON_PATH = "data/commercial.json";
    private static final String LAND_JSON_PATH = "data/land.json";

    public List<Property> fetchResidentialProperties() {
        return getProperties(RESIDENTIAL_JSON_PATH);
    }

    public List<Property> fetchCommercialProperties() {
        return getProperties(COMMERCIAL_JSON_PATH);
    }

    public List<Property> fetchLandProperties() {
        return getProperties(LAND_JSON_PATH);
    }

    private List<Property> getProperties(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        List<Property> properties = null;
        try (InputStream inputStream = new ClassPathResource(jsonPath).getInputStream()) {
            if (inputStream == null) {
                System.out.println("InputStream for " + jsonPath + " is null");
                return null;
            }
            properties = mapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Property fetchPropertyById(String category, Integer propertyId) {
        List<Property> properties = switch (category) {
            case "residential" -> fetchResidentialProperties();
            case "commercial" -> fetchCommercialProperties();
            case "land" -> fetchLandProperties();
            default -> null;
        };
        if (properties == null) {
            return null;
        }
        for (Property property : properties) {
            if (property.getId() == propertyId) {
                return property;
            }
        }
        return null;
    }
}
