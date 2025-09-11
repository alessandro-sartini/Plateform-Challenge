package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlacesResponse {
    @JsonProperty("html_attributions")
    private List<String> htmlAttributions;
    private Result result;
    private String status;

    // Getters e setters

    public List<String> getHtmlAttributions() {
        return this.htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
