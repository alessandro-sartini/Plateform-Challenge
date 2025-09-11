package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Photo {

    private int height;
    @JsonProperty("html_attributions")
    private List<String> htmlAttributions;
    @JsonProperty("photo_reference")
    private String photoReference;
    private int width;

    // Getters e setters

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getHtmlAttributions() {
        return this.htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getPhotoReference() {
        return this.photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
