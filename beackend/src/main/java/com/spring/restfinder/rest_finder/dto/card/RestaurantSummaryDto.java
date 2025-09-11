package com.spring.restfinder.rest_finder.dto.card;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.restfinder.rest_finder.config.AppProperties;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.model.Restaurant;

/*
DTO di sintesi per la lista dei ristoranti (card view)
*/
public class RestaurantSummaryDto {
    private Long id;
    private String placeId;
    private String primaryImage;
    private Float rating;
    private Integer priceCategory;
    private String city;
    private String name;
    private String slug;
    private boolean isVisible;
    private List<String> categories;
    private Long viewCount;
    private List<String> itCategories;

    private Double latitude;
    private Double longitude;

    // possibile rimozione futura
    private boolean isPartner;

    public static RestaurantSummaryDto fromEntity(Restaurant r) {

        RestaurantSummaryDto dto = new RestaurantSummaryDto();
        dto.id = r.getId();
        dto.primaryImage = r.getPrimaryImage();
        dto.rating = r.getRating();
        dto.priceCategory = r.getPriceCategory();
        dto.city = r.getCity();
        dto.name = r.getName();
        dto.isVisible = r.getIsVisible();
        dto.placeId = r.getPlaceId();
        dto.slug = r.getSlug();
        dto.isPartner = r.getIsPartner();
        dto.latitude = r.getLatitude();
        dto.longitude = r.getLongitude();
        dto.viewCount = r.getViewCount();
        if (r.getCategories() != null) {
            dto.categories = r.getCategories().stream()
                    .filter(Category::getIsVisible)
                    .map(Category::getName)
                    .collect(Collectors.toList());

            dto.itCategories = r.getCategories().stream()
                    .filter(Category::getIsVisible)
                    .map(Category::getItName) 
                    .collect(Collectors.toList());
        } else {
            dto.categories = List.of();
        }
        return dto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimaryImage() {

        String baseUrl = AppProperties.getBaseUrl();

        return baseUrl + this.primaryImage;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setPrimaryImage(String primaryImage) {

        this.primaryImage = primaryImage;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getPriceCategory() {
        return this.priceCategory;
    }

    public void setPriceCategory(Integer priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsVisible() {
        return this.isVisible;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean getIsPartner() {
        return this.isPartner;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public boolean isIsPartner() {
        return this.isPartner;
    }

    public List<String> getItCategories() {
        return this.itCategories;
    }

    public void setItCategories(List<String> itCategories) {
        this.itCategories = itCategories;
    }

}
