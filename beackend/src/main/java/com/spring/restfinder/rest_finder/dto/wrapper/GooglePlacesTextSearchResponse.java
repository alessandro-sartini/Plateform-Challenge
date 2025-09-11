package com.spring.restfinder.rest_finder.dto.wrapper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.restfinder.rest_finder.dto.restaurantGoogle.Result;

/**
 * Wrapper per il JSON di risposta della Text Search di Google Places
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlacesTextSearchResponse {

    @JsonProperty("results")
    private List<Result> results;

    @JsonProperty("next_page_token")
    private String nextPageToken;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

}
