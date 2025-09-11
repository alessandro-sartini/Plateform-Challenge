package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    @JsonProperty("formatted_phone_number")
    private String formattedPhoneNumber;

    @JsonProperty("geometry")
    private Geometry geometry;

    @JsonProperty("opening_hours")
    private OpeningHours openingHours;

    @JsonProperty("photos")
    private List<Photo> photos;

    @JsonProperty("price_level")
    private Integer priceLevel;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("user_ratings_total")
    private Integer userRatingsTotal;

    @JsonProperty("types")
    private List<String> types;

    @JsonProperty("url")
    private String url;

    @JsonProperty("website")
    private String website;

    @JsonProperty("reviews")
    private List<Review> reviews;

    // Getters e setters
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        if (types == null) {
            this.types = null;
            return;
        }
        this.types = types.stream()
                .map(Result::beautifyCategory)
                .toList();
    }

    public static String beautifyCategory(String raw) {
        if (raw == null)
            return "";
        String[] parts = raw.split("[_\\-]");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (!p.isEmpty()) {
                sb.append(Character.toUpperCase(p.charAt(0)));
                sb.append(p.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}