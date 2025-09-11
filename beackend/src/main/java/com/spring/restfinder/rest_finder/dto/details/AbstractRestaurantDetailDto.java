package com.spring.restfinder.rest_finder.dto.details;

import java.math.BigDecimal;
import java.util.List;

// import com.spring.restfinder.rest_finder.config.AppProperties;

public abstract class AbstractRestaurantDetailDto {
    private Long id;
    private List<String> imageUrls;
    private BigDecimal averagePrice;
    private Float rating;
    private String address;
    private String city;
    private String postalCode;
    private String province;
    private String country;
    private Double latitude;
    private Double longitude;
    private String placeId;
    private String menuLink;
    private Integer priceCategory;
    private Long googleRank;
    private Integer reviewCount;
    private String mapsUrl;
    private String websiteUrl;
    private Long plateformClientId;
    private String bookingLink;
    // private String primaryImage;
    private String name;
    private String slug;

    // possibile rimozione futura
    private boolean isPartner;
    // questo campo per le categorie
    private List<String> categories;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImageUrls() {
        return this.imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }


    public BigDecimal getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Float getRating() {
        return this.rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getMenuLink() {
        return this.menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public Integer getPriceCategory() {
        return this.priceCategory;
    }

    public void setPriceCategory(Integer priceCategory) {
        this.priceCategory = priceCategory;
    }

    public Long getGoogleRank() {
        return this.googleRank;
    }

    public void setGoogleRank(Long googleRank) {
        this.googleRank = googleRank;
    }

    public Integer getReviewCount() {
        return this.reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getMapsUrl() {
        return this.mapsUrl;
    }

    public void setMapsUrl(String mapsUrl) {
        this.mapsUrl = mapsUrl;
    }

    public String getWebsiteUrl() {
        return this.websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Long getPlateformClientId() {
        return this.plateformClientId;
    }

    public void setPlateformClientId(Long plateformClientId) {
        this.plateformClientId = plateformClientId;
    }

    public String getBookingLink() {
        return this.bookingLink;
    }

    public void setBookingLink(String bookingLink) {
        this.bookingLink = bookingLink;
    }

    // public String getPrimaryImage() {

    //     String baseUrl = AppProperties.getBaseUrl();

    //     return baseUrl + this.primaryImage;
    // }

    // public void setPrimaryImage(String primaryImage) {
    //     this.primaryImage = primaryImage;
    // }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isIsPartner() {
        return this.isPartner;
    }

    public boolean getIsPartner() {
        return this.isPartner;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
