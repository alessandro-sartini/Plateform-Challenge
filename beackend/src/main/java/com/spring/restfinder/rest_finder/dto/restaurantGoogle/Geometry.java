package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private Location location;
    private Viewport viewport;

    // Getters e setters

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Viewport getViewport() {
        return this.viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

}