package com.spring.restfinder.rest_finder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // max 100 caratteri
    @Column(name = "name")
    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(max = 100, message = "Il nome non può superare i 255 caratteri")
    private String name;

    // @Column(name = "services")
    // @Size(max = 100, message = "I servizi non possono superare i 1000 caratteri")
    // private String services;

    @Column(name = "average_price", precision = 10, scale = 2)
    @DecimalMin(value = "0.0", message = "Il prezzo medio non può essere negativo")
    @Digits(integer = 8, fraction = 2, message = "Il prezzo medio deve avere massimo 8 cifre intere e 2 decimali")
    private BigDecimal averagePrice;

    @Column(name = "rating")
    @Min(value = 0, message = "Il rating non può essere inferiore a 0")
    @Max(value = 5, message = "Il rating non può essere superiore a 5")
    private Float rating;

    // Indirizzo , max 255 caratteri
    @Column(name = "address")
    @NotBlank(message = "L'indirizzo non può essere vuoto")
    @Size(max = 255, message = "L'indirizzo non può superare i 255 caratteri")
    private String address;

    // Città , max 100 caratteri
    @Column(name = "city")
    @NotBlank(message = "La città non può essere vuota")
    @Size(max = 100, message = "La città non può superare i 100 caratteri")
    private String city;

    // CAP , pattern solo 5 numeri
    @Column(name = "postal_code")
    // @NotBlank(message = "Il codice postale non può essere vuoto")
    @Pattern(regexp = "^[0-9]{5}$", message = "Il codice postale deve essere di 5 cifre numeriche")
    private String postalCode;

    // Provincia , max 100 caratteri
    @Column(name = "province")
    // @NotBlank(message = "La provincia non può essere vuota")
    @Size(max = 100, message = "La provincia non può superare i 100 caratteri")
    private String province;

    // Stato , max 100 caratteri
    @Column(name = "country")
    // @NotBlank(message = "Il paese non può essere vuoto")
    @Size(max = 100, message = "Il paese non può superare i 100 caratteri")
    private String country;

    // Latitudine obbligatoria, tra -90 e 90
    @Column(name = "latitude")
    @NotNull(message = "La latitudine non può essere nulla")
    @DecimalMin(value = "-90.0", message = "La latitudine minima è -90.0")
    @DecimalMax(value = "90.0", message = "La latitudine massima è 90.0")
    private Double latitude;

    // Longitudine obbligatoria, tra -180 e 180
    @Column(name = "longitude")
    @NotNull(message = "La longitudine non può essere nulla")
    @DecimalMin(value = "-180.0", message = "La longitudine minima è -180.0")
    @DecimalMax(value = "180.0", message = "La longitudine massima è 180.0")
    private Double longitude;

    // PlaceId , max 255 caratteri, unico
    @Column(name = "place_id", unique = true)
    @NotBlank(message = "Il Place ID non può essere vuoto")
    @Size(max = 255, message = "Il Place ID non può superare i 255 caratteri")
    private String placeId;

    // Immagine principale opzionale, max 1024 caratteri
    @Column(name = "primary_image", length = 1024)
    @Size(max = 1024, message = "L'URL dell'immagine principale non può superare i 1024 caratteri")
    private String primaryImage;

    // Menu link opzionale, max 1024 caratteri
    @Column(name = "menu_link")
    @Size(max = 1024, message = "L'URL del menu non può superare i 1024 caratteri")
    private String menuLink;

    // Telefono opzionale, max 20 caratteri, pattern internazionale base
    @Column(name = "phone_number")
    @Pattern(regexp = "^(\\+?[0-9\\s\\-()]{7,20})$", message = "Il formato del numero di telefono non è valido")
    @Size(max = 20, message = "Il numero di telefono non può superare i 20 caratteri")
    private String phoneNumber;

    // Categoria prezzo OBBLIGATORIA, tra 0 e 4
    @Column(name = "price_category")
    // @NotNull(message = "La categoria di prezzo non può essere nulla")
    @Min(value = 0, message = "La categoria di prezzo minima è 0")
    @Max(value = 4, message = "La categoria di prezzo massima è 4")
    private Integer priceCategory;

    @Column(name = "google_rank")
    @Min(value = 0, message = "Il Google Rank non può essere negativo")
    private Long googleRank;

    @Column(name = "review_count")
    @Min(value = 0, message = "Il conteggio delle recensioni non può essere negativo")
    private Integer reviewCount;

    // URL opzionali, max 1024 caratteri
    @Column(name = "maps_url")
    @Size(max = 1024, message = "L'URL di Google Maps non può superare i 1024 caratteri")
    private String mapsUrl;

    @Column(name = "website_url")
    @Size(max = 1024, message = "L'URL del sito web non può superare i 1024 caratteri")
    private String websiteUrl;

    @Column(name = "plateform_client_id")
    private Long plateformClientId;

    @Column(name = "booking_link")
    @Size(max = 1024, message = "L'URL di prenotazione non può superare i 1024 caratteri")
    private String bookingLink;

    @Column(name = "visible", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isVisible = true;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "view_count", columnDefinition = "BIGINT DEFAULT 0")
    private Long viewCount = 0L;

    // possibile rimozione futura
    @Column(name = "is_partner", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isPartner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImgRestaurant> images = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "categories_resturants", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        updateSlug();
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
        updateSlug();
    }

    public String getPrimaryImage() {
        return this.primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public String getMenuLink() {
        return this.menuLink;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void addCategory(Category c) {
        this.categories.add(c);
        c.getRestaurants().add(this);
    }

    public List<ImgRestaurant> getImages() {
        return this.images;
    }

    public void setImages(List<ImgRestaurant> imgs) {
        this.images = imgs;
    }

    public boolean isIsVisible() {
        return this.isVisible;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    private void updateSlug() {
        if (this.name != null && this.placeId != null) {
            this.slug = (this.name.trim() + "-" + this.placeId.trim()).replaceAll("\\s+", "-").toLowerCase();
        }
    }

    public Long getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }


    public boolean getIsPartner() {
        return this.isPartner;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }

}
