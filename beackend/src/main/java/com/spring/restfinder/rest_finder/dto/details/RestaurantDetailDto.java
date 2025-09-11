package com.spring.restfinder.rest_finder.dto.details;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.restfinder.rest_finder.model.Restaurant;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.config.AppProperties;

public class RestaurantDetailDto extends AbstractRestaurantDetailDto {
    private boolean isVisible;
    private String phoneNumber;
    // Se hai campi aggiuntivi solo per questo DTO, mettili qui
    // (in questo caso non ce ne sono, ma volendo puoi aggiungere es: extraField)

    // Conversione da entity a DTO
    public static RestaurantDetailDto fromEntity(Restaurant r) {
        RestaurantDetailDto dto = new RestaurantDetailDto();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setIsVisible(r.getIsVisible());
        dto.setAveragePrice(r.getAveragePrice());
        dto.setRating(r.getRating());
        dto.setAddress(r.getAddress());
        dto.setCity(r.getCity());
        dto.setPostalCode(r.getPostalCode());
        dto.setProvince(r.getProvince());
        dto.setCountry(r.getCountry());
        dto.setLatitude(r.getLatitude());
        dto.setLongitude(r.getLongitude());
        dto.setPlaceId(r.getPlaceId());
        dto.setMenuLink(r.getMenuLink());
        dto.setPhoneNumber(r.getPhoneNumber());
        dto.setPriceCategory(r.getPriceCategory());
        dto.setGoogleRank(r.getGoogleRank());
        dto.setReviewCount(r.getReviewCount());
        dto.setMapsUrl(r.getMapsUrl());
        dto.setWebsiteUrl(r.getWebsiteUrl());
        dto.setPlateformClientId(r.getPlateformClientId());
        dto.setBookingLink(r.getBookingLink());
        dto.setSlug(r.getSlug());
        dto.setIsPartner(r.getIsPartner());

        String baseUrl = AppProperties.getBaseUrl();
        if (r.getImages() != null && !r.getImages().isEmpty()) {
            dto.setImageUrls(r.getImages().stream()
                .map(img -> baseUrl + img.getPath())
                .collect(Collectors.toList()));
        } else {
            dto.setImageUrls(List.of());
        }

        if (r.getCategories() != null) {
            dto.setCategories(r.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toList()));
        } else {
            dto.setCategories(List.of());
        }

        return dto;
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


    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
