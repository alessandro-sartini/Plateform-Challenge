package com.spring.restfinder.rest_finder.dto.details;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.restfinder.rest_finder.model.Restaurant;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.config.AppProperties;

public class ApiRestaurantDetailDto extends AbstractRestaurantDetailDto {
    

    // Conversione da entity a DTO
    public static ApiRestaurantDetailDto fromEntity(Restaurant r) {
        ApiRestaurantDetailDto dto = new ApiRestaurantDetailDto();
        dto.setId(r.getId());
        dto.setName(r.getName());
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


}
