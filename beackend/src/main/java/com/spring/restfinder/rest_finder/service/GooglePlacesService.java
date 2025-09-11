package com.spring.restfinder.rest_finder.service;

import com.spring.restfinder.rest_finder.dto.wrapper.GooglePlacesTextSearchResponse;
import com.spring.restfinder.rest_finder.dto.restaurantGoogle.GooglePlacesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriUtils;

@Service
public class GooglePlacesService {

    @Value("${google.places.api.key}")
    private String apiKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * TEXT SEARCH: ricerca multipla ristoranti per nome/città ecc
     */
    public Map<String, Object> cercaRisultatiConToken(String query, String nextPageToken) {
        String url;
        if (nextPageToken != null) {
            url = "https://maps.googleapis.com/maps/api/place/textsearch/json"
                    + "?pagetoken=" + UriUtils.encode(nextPageToken, StandardCharsets.UTF_8)
                    + "&type=restaurant"

                    + "&language=it"
                    + "&key=" + apiKey;
        } else {
            url = "https://maps.googleapis.com/maps/api/place/textsearch/json"
                    + "?query=" + UriUtils.encode(query, StandardCharsets.UTF_8)
                    + "&type=restaurant"
                    + "&language=it"
                    + "&key=" + apiKey;
        }

        try {
            if (nextPageToken != null)
                Thread.sleep(3500);
            String json = restTemplate.getForObject(url, String.class);
            GooglePlacesTextSearchResponse resp = objectMapper.readValue(json, GooglePlacesTextSearchResponse.class);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("results", resp.getResults() != null ? resp.getResults() : Collections.emptyList());
            resultMap.put("next_page_token", resp.getNextPageToken());
            return resultMap;
        } catch (Exception e) {
            throw new RuntimeException("Errore Google TextSearch: " + e.getMessage(), e);
        }
    }

    // public Map<String, Object> cercaRistorantiNearby(double lat, double lng,
    // Integer radius, String nextPageToken) {
    // String url;
    // if (nextPageToken != null) {
    // url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"
    // + "?pagetoken=" + UriUtils.encode(nextPageToken, StandardCharsets.UTF_8)
    // + "&language=it"
    // + "&key=" + apiKey;
    // } else {
    // url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json"
    // + "?location=" + lat + "," + lng
    // + "&radius=" + (radius != null ? radius : 1500)
    // + "&type=restaurant"
    // + "&language=it"
    // + "&key=" + apiKey;
    // }

    // try {
    // if (nextPageToken != null)
    // Thread.sleep(3500); // come prima

    // String json = restTemplate.getForObject(url, String.class);
    // GooglePlacesTextSearchResponse resp = objectMapper.readValue(json,
    // GooglePlacesTextSearchResponse.class);

    // Map<String, Object> resultMap = new HashMap<>();
    // resultMap.put("results", resp.getResults() != null ? resp.getResults() :
    // Collections.emptyList());
    // resultMap.put("next_page_token", resp.getNextPageToken());
    // return resultMap;
    // } catch (Exception e) {
    // throw new RuntimeException("Errore Google NearbySearch: " + e.getMessage(),
    // e);
    // }
    // }

    /**
     * PLACE DETAILS: dettaglio ristorante per Place ID
     */
    public GooglePlacesResponse getPlaceDetails(String placeId) {
        String url = "https://maps.googleapis.com/maps/api/place/details/json" +
                "?place_id=" + placeId +
                "&fields=name,formatted_address,geometry,formatted_phone_number,website,opening_hours,photos,user_ratings_total,price_level,types,url,review,rating"
                + "&language=it"
                + "&key=" + apiKey;
        try {
            String json = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(json, GooglePlacesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Errore nel recupero dei dati da Google Places API: " + e.getMessage(), e);
        }
    }
}
