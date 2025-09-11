package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Viewport {
    private Location northeast;
    private Location southwest;

    // Getters e setters

    public Location getNortheast() {
        return this.northeast;
    }

    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    public Location getSouthwest() {
        return this.southwest;
    }

    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }

}
