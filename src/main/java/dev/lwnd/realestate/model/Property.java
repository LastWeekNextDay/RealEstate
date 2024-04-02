package dev.lwnd.realestate.model;

import com.vaadin.flow.internal.Pair;

import java.util.List;

public class Property {
    private int id;
    private String name;
    private float price;
    private float size;
    private List<String> imagesUrl;
    private String location;

    public Property() {
    }

    public Property(int id, String name, float price, float size, List<String> imagesUrl, String location) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.imagesUrl = imagesUrl;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Pair<Float, Float> getCoordinates() {
        String[] coordinates = location.split(",");
        return new Pair<>(Float.parseFloat(coordinates[0]), Float.parseFloat(coordinates[1]));
    }
}
