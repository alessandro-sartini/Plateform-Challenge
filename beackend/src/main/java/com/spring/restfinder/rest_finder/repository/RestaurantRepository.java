package com.spring.restfinder.rest_finder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.restfinder.rest_finder.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

        Page<Restaurant> findAllByOrderByViewCountDesc(Pageable pageable);

        List<Restaurant> findTop10ByOrderByViewCountDesc();

        boolean existsByPlaceId(String placeId);

        Optional<Restaurant> findBySlug(String slug);

        boolean existsBySlug(String slug);

        List<Restaurant> findByNameContainingIgnoreCase(String name);

        List<Restaurant> findByCityIgnoreCase(String city);

        List<Restaurant> findByCategories_Id(Long categoryId);

        List<Restaurant> findByPriceCategoryLessThanEqual(Integer priceCategory);

        List<Restaurant> findByRatingGreaterThanEqual(Float minRating);

        Page<Restaurant> findAll(Pageable pageable);

        Page<Restaurant> findByNameContainingIgnoreCaseAndCityContainingIgnoreCase(String name, String city,
                        Pageable pageable);

        Page<Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);

        // Page<Restaurant> findByCityContainingIgnoreCase(String city, Pageable pageable);

        // Page<Restaurant> findByCityContainingIgnoreCaseAndfindByNameContainingIgnoreCase(String city, String name, Pageable pageable);

        Page<Restaurant> findByCategories_Id(Long categoryId, Pageable pageable);

        // SOLO VISIBILI
        Page<Restaurant> findByIsVisible(Boolean isVisible, Pageable pageable);

        Page<Restaurant> findByCategories_IdAndIsVisible(Long categoryId, Boolean isVisible, Pageable pageable);

        Page<Restaurant> findByIsVisibleAndPriceCategory(Boolean isVisible, Integer priceCategory, Pageable pageable);

        Page<Restaurant> findByCategories_IdAndIsVisibleAndPriceCategory(Long categoryId, Boolean isVisible,
                        Integer priceCategory, Pageable pageable);

        Page<Restaurant> findByCityContainingIgnoreCaseAndIsVisibleAndPriceCategory(String city, Boolean isVisible,
                        Integer priceCategory, Pageable pageable);

        Page<Restaurant> findByIsVisibleAndRatingGreaterThanEqual(boolean isVisible, Float minRating,
                        Pageable pageable);

        Page<Restaurant> findByNameContainingIgnoreCaseAndCityContainingIgnoreCaseAndIsVisible(String name, String city,
                        boolean isVisible, Pageable pageable);

        Page<Restaurant> findByNameContainingIgnoreCaseAndIsVisible(String name, boolean isVisible, Pageable pageable);

        Page<Restaurant> findByCityContainingIgnoreCaseAndIsVisible(String city, boolean isVisible, Pageable pageable);

        // FILTRI COMPLETI
        Page<Restaurant> findByNameContainingIgnoreCaseAndCityContainingIgnoreCaseAndCategories_IdAndIsVisibleAndRatingGreaterThanEqualAndPriceCategory(
                        String name, String city, Long categoryId, Boolean isVisible, Float rating,
                        Integer priceCategory, Pageable pageable);

        Page<Restaurant> findByNameContainingIgnoreCaseAndCityContainingIgnoreCaseAndCategories_IdAndIsVisibleAndRatingGreaterThanEqual(
                        String name, String city, Long categoryId, Boolean isVisible, Float rating, Pageable pageable);

        Page<Restaurant> findByRatingGreaterThanEqual(Float rating, Pageable pageable);

        Page<Restaurant> findByPriceCategoryLessThanEqual(Integer priceCategory, Pageable pageable);

        Page<Restaurant> findByPriceCategory(Integer priceCategory, Pageable pageable);

        Optional<Restaurant> findByPlaceId(String placeId);

        Page<Restaurant> findAll(Specification<Restaurant> spec, Pageable pageable);
}
