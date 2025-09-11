package com.spring.restfinder.rest_finder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.restfinder.rest_finder.dto.card.RestaurantSummaryDto;
import com.spring.restfinder.rest_finder.dto.details.RestaurantDetailDto;
import com.spring.restfinder.rest_finder.dto.restaurantGoogle.GooglePlacesResponse;
import com.spring.restfinder.rest_finder.dto.restaurantGoogle.Photo;
import com.spring.restfinder.rest_finder.dto.restaurantGoogle.Result;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.model.ImgRestaurant;
import com.spring.restfinder.rest_finder.model.Restaurant;
import com.spring.restfinder.rest_finder.repository.CategoryRepository;
import com.spring.restfinder.rest_finder.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService {

    @Value("${google.places.api.key}")
    private String apiKey;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Optional<Restaurant> findByPlaceId(String placeId) {
        return restaurantRepository.findByPlaceId(placeId);
    }

    private String buildPhotoUrl(String photoReference) {
        return String.format(
                "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=%s&key=%s",
                photoReference, apiKey);
    }

    @Transactional
    public RestaurantDetailDto findDetailByPlaceId(String placeId) {
        // 1) chiama Google
        GooglePlacesResponse response = this.getPlaceDetails(placeId);

        // 2) estrai il Result
        var result = response.getResult();

        // 3) mappa nei campi del tuo DTO
        RestaurantDetailDto dto = new RestaurantDetailDto();
        dto.setName(result.getName());
        dto.setAddress(result.getFormattedAddress());
        dto.setPhoneNumber(result.getFormattedPhoneNumber());
        dto.setLatitude(result.getGeometry().getLocation().getLat());
        dto.setLongitude(result.getGeometry().getLocation().getLng());

        // 4) tipi e categorie
        dto.setCategories(result.getTypes());
        // se già popolato Category in DB:
        // dto.setCategories(restaurantRepo.findByPlaceId(placeId)
        // .map(r -> r.getCategories().stream().map(Category::getName).toList())
        // .orElse(List.of()));
        // per semplicità, mostriamo per ora i types come categorie:
        dto.setCategories(result.getTypes());

        return dto;
    }

    public GooglePlacesResponse getPlaceDetails(String placeId) {

        String url = "https://maps.googleapis.com/maps/api/place/details/json" +
                "?place_id=" + placeId +
                "&fields=name,formatted_address,geometry,formatted_phone_number,website,opening_hours,photos,user_ratings_total,price_level,types,url,review,rating"
                +
                "&language=it" +
                "&key=" + apiKey;

        try {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return objectMapper.readValue(jsonResponse, GooglePlacesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Errore nel recupero dei dati da Google Places API: " + e.getMessage(), e);
        }
    }

    public Page<RestaurantSummaryDto> searchPaged(
            String name,
            String city,
            int page,
            int size,
            Long categoryId,
            Boolean isVisible,
            Float minRating,
            Integer priceCategory) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Restaurant> spec = (root, query, cb) -> cb.conjunction();

        if (name != null && !name.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (city != null && !city.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.join("categories").get("id"), categoryId));
        }
        if (isVisible != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("isVisible"), isVisible));
        }
        if (minRating != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rating"), minRating));
        }
        if (priceCategory != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("priceCategory"), priceCategory));
        }

        Page<Restaurant> restaurants = restaurantRepository.findAll(spec, pageable);
        return restaurants.map(RestaurantSummaryDto::fromEntity);
    }

    public Page<RestaurantSummaryDto> searchPagedRest(
            String search,
            int page,
            int size,
            String categoryName,
            Float minRating,
            Integer priceCategory) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Restaurant> spec = (root, query, cb) -> cb.isTrue(root.get("isVisible"));

        if (search != null && !search.isBlank()) {
            String likeSearch = "%" + search.toLowerCase() + "%";
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(cb.lower(root.get("name")), likeSearch),
                    cb.like(cb.lower(root.get("city")), likeSearch)));
        }
        if (categoryName != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.join("categories").get("name"), categoryName));
        }
        if (minRating != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("rating"), minRating));
        }
        if (priceCategory != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("priceCategory"), priceCategory));
        }

        Page<Restaurant> restaurants = restaurantRepository.findAll(spec, pageable);
        return restaurants.map(RestaurantSummaryDto::fromEntity);
    }

    public List<Restaurant> findAll() {
        List<Restaurant> list = restaurantRepository.findAll();

        return list;

    }

    public Page<Restaurant> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return restaurantRepository.findAll(pageable);
    }

    public Page<Restaurant> findByNamePaged(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return restaurantRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    // public Page<Restaurant> getPagedRestaurants(String city, int page, int size)
    // {
    // Pageable pageable = PageRequest.of(page, size);
    // if (city == null || city.isBlank()) {
    // return restaurantRepository.findAll(pageable);
    // }
    // return restaurantRepository.findByCityContainingIgnoreCase(city, pageable);
    // }

    public List<RestaurantSummaryDto> findAllSummary() {
        List<Restaurant> list = restaurantRepository.findAll();
        List<RestaurantSummaryDto> summary = new ArrayList<>();

        for (Restaurant restaurant : list) {
            summary.add(RestaurantSummaryDto.fromEntity(restaurant));
        }
        return summary;

    }

    public Optional<Restaurant> findById(long id) {

        return restaurantRepository.findById(id);

    }

    public List<Restaurant> findByName(String name) {

        return restaurantRepository.findByNameContainingIgnoreCase(name);

    }

    public Restaurant getByIdRestaurant(long id) {

        Optional<Restaurant> restaurantAttempt = restaurantRepository.findById(id);
        return restaurantAttempt.get();
    }

    public Optional<Restaurant> getByIdOptionalRestaurant(long id) {

        Optional<Restaurant> restaurantAttempt = restaurantRepository.findById(id);
        return restaurantAttempt;
    }

    public Optional<RestaurantDetailDto> findDetailById(long id) {
        return restaurantRepository.findById(id)
                .map(RestaurantDetailDto::fromEntity);
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    public Optional<Restaurant> save(Restaurant restaurant) {
        if (restaurantRepository.existsByPlaceId(restaurant.getPlaceId())) {
            throw new com.spring.restfinder.rest_finder.exception.RestaurantAlreadyExistsException(
                    "Ristorante già importato!");
        }
        return Optional.of(restaurantRepository.save(restaurant));
    }

    public Optional<Void> deleteById(Long id) {
        if (restaurantRepository.existsById(id)) {
            restaurantRepository.deleteById(id);
            return Optional.ofNullable(null);
        }
        return Optional.empty();
    }

    public List<Restaurant> findByCity(String city) {
        List<Restaurant> list = restaurantRepository.findByCityIgnoreCase(city);
        return list;
    }

    public List<Restaurant> findByCategoryId(Long categoryId) {
        List<Restaurant> list = restaurantRepository.findByCategories_Id(categoryId);
        return list;
    }

    public List<Restaurant> findByPriceCategory(Integer priceCategory) {
        List<Restaurant> list = restaurantRepository.findByPriceCategoryLessThanEqual(priceCategory);
        return list;
    }

    public List<Restaurant> findByRatingGreaterThanEqual(Float minRating) {
        List<Restaurant> list = restaurantRepository.findByRatingGreaterThanEqual(minRating);
        return list;
    }

    public Page<RestaurantSummaryDto> findMostViewedPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurantPage = restaurantRepository.findAllByOrderByViewCountDesc(pageable);
        return restaurantPage.map(RestaurantSummaryDto::fromEntity);
    }

    public Restaurant update(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delate(Long id) {
        Restaurant restaurant = getByIdRestaurant(id);

        restaurantRepository.delete(restaurant);
    }

    public boolean existsById(long id) {
        return restaurantRepository.existsById(id);
    }

    public Optional<Restaurant> findBySlug(String slug) {
        return restaurantRepository.findBySlug(slug);
    }

    public Optional<RestaurantDetailDto> findDetailBySlug(String slug) {
        return restaurantRepository.findBySlug(slug)
                .map(RestaurantDetailDto::fromEntity);
    }

    public boolean existByRestaurant(Restaurant restaurant) {

        return restaurantRepository.existsById(restaurant.getId());
    }

    /**
     * Scompone un formattedAddress del tipo
     * "Via Varesina, 61, 20156 Milano MI, Italia"
     * in address, postalCode, city, province, country.
     */
    private void populateAddressComponents(Restaurant r, String formattedAddress) {
        // 1) split su virgola
        String[] parts = formattedAddress.split(",");
        // parts = ["Via Varesina", " 61", " 20156 Milano MI", " Italia"]

        // 2) via + numero civico
        if (parts.length >= 2) {
            String street = parts[0].trim();
            String number = parts[1].trim();
            r.setAddress(street + (number.isEmpty() ? "" : ", " + number));
        } else {
            r.setAddress(formattedAddress);
        }

        // 3) CAP, città e provincia
        if (parts.length >= 3) {
            String capCityProv = parts[2].trim();
            // varesa "20156 Milano MI"
            // split in [cap, rest...]
            String[] ccp = capCityProv.split("\\s+", 2);
            if (ccp.length >= 2) {
                r.setPostalCode(ccp[0]);
                // rest = "Milano MI"
                String[] cityProv = ccp[1].split("\\s+");
                // cityProv = ["Milano", "MI"]
                r.setCity(cityProv[0]);
                if (cityProv.length >= 2) {
                    r.setProvince(cityProv[1]);
                }
            } else {
                r.setCity(capCityProv);
            }
        }

        // 4) nazione (country)
        if (parts.length >= 4) {
            r.setCountry(parts[3].trim());
        }
    }

    @Transactional
    public void incrementViewCount(Long id) {
        restaurantRepository.findById(id).ifPresent(r -> {
            if (r.getViewCount() == null)
                r.setViewCount(0L);
            r.setViewCount(r.getViewCount() + 1);
            restaurantRepository.save(r);
        });
    }

    @Transactional
    public void incrementViewCount(String slug) {
        restaurantRepository.findBySlug(slug).ifPresent(r -> {
            if (r.getViewCount() == null)
                r.setViewCount(0L);
            r.setViewCount(r.getViewCount() + 1);
            restaurantRepository.save(r);
        });
    }

    @Transactional
    public Restaurant saveFromGooglePlaceId(String placeId) {
        GooglePlacesResponse resp = getPlaceDetails(placeId);
        Result result = resp.getResult();

        Restaurant r = new Restaurant();
        r.setPlaceId(placeId);
        r.setName(result.getName());
        r.setPhoneNumber(result.getFormattedPhoneNumber());
        r.setLatitude(result.getGeometry().getLocation().getLat());
        r.setLongitude(result.getGeometry().getLocation().getLng());
        r.setMapsUrl(result.getUrl());
        r.setReviewCount(result.getUserRatingsTotal());
        r.setReviewCount(result.getUserRatingsTotal());

        if (result.getRating() != null)
            r.setRating(result.getRating().floatValue());
        populateAddressComponents(r, result.getFormattedAddress());
        r.setPriceCategory(result.getPriceLevel());
        r.setWebsiteUrl(result.getWebsite());
        r = restaurantRepository.save(r);

        if (result.getPhotos() != null) {
            List<ImgRestaurant> imgs = new ArrayList<>();
            int idx = 1;
            for (Photo photo : result.getPhotos().stream().limit(3).toList()) {
                try {
                    String googleUrl = buildPhotoUrl(photo.getPhotoReference());
                    String localPath = imageStorageService.downloadAndStore(r.getId(), googleUrl, "photo" + (idx++));
                    ImgRestaurant img = new ImgRestaurant();
                    img.setPath(localPath);
                    img.setRestaurant(r);
                    imgs.add(img);
                } catch (Exception e) {
                    // Log error but continue with other photos
                    System.err.println("Error downloading photo: " + e.getMessage());
                    continue;
                }
            }
            r.getImages().addAll(imgs);
            if (!imgs.isEmpty()) {
                r.setPrimaryImage(imgs.get(0).getPath());
            }
        }

        for (String t : result.getTypes()) {
            Category c = findOrCreateCategory(t);
            r.addCategory(c);
        }

        return restaurantRepository.save(r);
    }

    public Category findOrCreateCategory(String name) {
        Optional<Category> existing = categoryRepository.findByName(name);
        return existing.orElseGet(() -> {
            Category c = new Category();
            c.setName(name);
            return categoryRepository.save(c);
        });
    }

    public Page<RestaurantSummaryDto> findMostViewedPaged(int page, int size, String order) {
        Pageable pageable;
        if ("asc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("viewCount").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("viewCount").descending());
        }
        return restaurantRepository.findAll(pageable).map(RestaurantSummaryDto::fromEntity);
    }

}

// public RestaurantDetailDto getById(long id) {

// Optional<Restaurant> restaurantAttempt = restaurantRepository.findById(id);

// return RestaurantDetailDto.fromEntity(restaurantAttempt.get());
// }

// public Page<RestaurantSummaryDto> findAllSummaryPaged(int page, int size) {
// Pageable pageable = PageRequest.of(page, size);
// Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
// List<RestaurantSummaryDto> dtoList = restaurantPage.getContent().stream()
// .map(RestaurantSummaryDto::fromEntity)
// .toList();
// return new PageImpl<>(dtoList, pageable, restaurantPage.getTotalElements());
// }

// public Page<RestaurantSummaryDto> findByCitySummaryPaged(String city, int
// page, int size) {
// Pageable pageable = PageRequest.of(page, size);
// Page<Restaurant> restaurantPage =
// restaurantRepository.findByCityContainingIgnoreCase(city, pageable);
// List<RestaurantSummaryDto> dtoList = restaurantPage.getContent().stream()
// .map(RestaurantSummaryDto::fromEntity)
// .toList();
// return new PageImpl<>(dtoList, pageable, restaurantPage.getTotalElements());
// }
// @Transactional
// public Restaurant saveFromGooglePlaceId(String placeId) {
// // 1) chiamata a Google
// GooglePlacesResponse resp = getPlaceDetails(placeId);
// Result result = resp.getResult();

// // 2) nuova entità
// Restaurant r = new Restaurant();
// r.setPlaceId(placeId);
// // System.out.println("DEBUG: sto salvando placeId = " + r.getPlaceId());
// // campi base
// r.setName(result.getName());
// r.setAddress(result.getFormattedAddress());
// r.setPhoneNumber(result.getFormattedPhoneNumber());
// // coordinate
// r.setLatitude(result.getGeometry().getLocation().getLat());
// r.setLongitude(result.getGeometry().getLocation().getLng());
// r.setMapsUrl(result.getUrl());
// // r.setRating(result.getRating().floatValue());
// r.setReviewCount(result.getUserRatingsTotal());

// // 𝗡𝗨𝗢𝗩𝗢: estrai fino a 3 photo_reference e costruisci le URL
// // 𝗡𝗨𝗢𝗩𝗢: crea fino a 3 ImgRestaurant
// if (result.getPhotos() != null) {
// List<ImgRestaurant> imgs = new ArrayList<>();
// int idx = 1;
// for (Photo photo : result.getPhotos().stream().limit(3).toList()) {
// String photoRef = photo.getPhotoReference();
// // genera URL Google
// String googleUrl = "https://maps.googleapis.com/maps/api/place/photo"
// + "?maxwidth=800"
// + "&photoreference=" + photoRef
// + "&key=" + apiKey;
// // scarica e salva fisicamente
// String localPath = imageStorageService.downloadAndStore(
// r.getId(), googleUrl, "photo" + (idx++));
// // crea l’entità
// ImgRestaurant img = new ImgRestaurant();
// img.setPath(localPath);
// img.setRestaurant(r);
// imgs.add(img);
// }
// r.getImages().addAll(imgs);
// // imposta la prima come primary
// if (!imgs.isEmpty()) {
// r.setPrimaryImage(imgs.get(0).getPath());
// }
// }
// // numero totale di recensioni (user_ratings_total)
// // r.setRating(result.getUserRatingsTotal());
// // URL del sito (website)
// r.setWebsiteUrl(result.getWebsite());
// // ----------------------------------------

// // rating medio
// if (result.getRating() != null) {
// r.setRating(result.getRating().floatValue());
// }
// String formattedAddress = result.getFormattedAddress();
// populateAddressComponents(r, formattedAddress);
// // categoria di prezzo
// r.setPriceCategory(result.getPriceLevel());

// // immagini Google → primaryImage + imageUrls
// if (result.getPhotos() != null && !result.getPhotos().isEmpty()) {
// String photoRef = result.getPhotos().get(0).getPhotoReference();
// String photoUrl = "https://maps.googleapis.com/maps/api/place/photo"
// + "?maxwidth=400&photoreference=" + photoRef
// + "&key=" + apiKey;
// r.setPrimaryImage(photoUrl);
// r.setImageUrls(List.of(photoUrl));
// }

// // types → Category many-to-many
// for (String t : result.getTypes()) {
// Category c = findOrCreateCategory(t);
// r.addCategory(c);
// }

// // 3) salva in DB
// return restaurantRepository.save(r);
// }
// @Transactional
// public Restaurant saveFromGooglePlaceId(String placeId) {
// GooglePlacesResponse response = this.getPlaceDetails(placeId);
// var result = response.getResult();

// Restaurant r = new Restaurant();
// r.setPlaceId(placeId);
// r.setName(result.getName());
// r.setAddress(result.getFormattedAddress());
// r.setPhoneNumber(result.getFormattedPhoneNumber());
// r.setLatitude(result.getGeometry().getLocation().getLat());
// r.setLongitude(result.getGeometry().getLocation().getLng());
// r.setReviewCount(result.getUserRatingsTotal());
// r.setRating(result.getRating() != null ? result.getRating().floatValue() :
// null);

// // estrai city dall'indirizzo
// String[] parts = result.getFormattedAddress().split(",");
// if (parts.length >= 2) {
// r.setCity(parts[parts.length - 2].trim());
// }

// // mapping dei types in Category senza stream
// List<Category> cats = new ArrayList<>();
// for (String typeName : result.getTypes()) {
// Optional<Category> opt = categoryRepository.findByName(typeName);
// Category cat;
// if (opt.isPresent()) {
// cat = opt.get();
// } else {
// cat = new Category();
// cat.setName(typeName);
// cat = categoryRepository.save(cat);
// }
// cats.add(cat);
// }
// r.setCategories(cats);

// return restaurantRepository.save(r);
// }

/**
 * Chiama Google Places API e deserializza la risposta.
 */
// public GooglePlacesResponse getPlaceDetails(String placeId) {
// String apiKey = "TUO_API_KEY";
// String url = "https://maps.googleapis.com/maps/api/place/details/json" +
// "?place_id=" + placeId +
// "&fields=name,formatted_address,geometry,formatted_phone_number,rating,user_ratings_total,types"
// +
// "&language=it" +
// "&key=" + apiKey;

// try {
// String json = restTemplate.getForObject(url, String.class);
// return objectMapper.readValue(json, GooglePlacesResponse.class);
// } catch (Exception e) {
// throw new RuntimeException("Errore Google Places: " + e.getMessage(), e);
// }
// }
