package com.spring.restfinder.rest_finder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.spring.restfinder.rest_finder.RestFinderApplication;
import com.spring.restfinder.rest_finder.dto.card.RestaurantSummaryDto;
import com.spring.restfinder.rest_finder.dto.details.RestaurantDetailDto;
import com.spring.restfinder.rest_finder.model.ImgRestaurant;
import com.spring.restfinder.rest_finder.model.Restaurant;
import com.spring.restfinder.rest_finder.service.CategoryService;
import com.spring.restfinder.rest_finder.service.ImageStorageService;
import com.spring.restfinder.rest_finder.service.ImgRestaurantService;
import com.spring.restfinder.rest_finder.service.RestaurantService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin/restaurants")
public class RestaurantsController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantsController.class);

    @SuppressWarnings("unused")
    private final RestFinderApplication restFinderApplication;

    private final CategoryService categoryService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ImgRestaurantService imgRestaurantService;

    @Autowired
    private ImageStorageService imageStorageService;

    RestaurantsController(CategoryService categoryService, RestFinderApplication restFinderApplication) {
        this.categoryService = categoryService;
        this.restFinderApplication = restFinderApplication;
    }

    @GetMapping
    public String index(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean isVisible,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Integer priceCategory) {

        Page<RestaurantSummaryDto> restaurantPage = restaurantService.searchPaged(
                query, city, page, size, categoryId, isVisible, minRating, priceCategory);

        model.addAttribute("restaurantPage", restaurantPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", restaurantPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("query", query);
        model.addAttribute("city", city);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("isVisible", isVisible);
        model.addAttribute("minRating", minRating);
        model.addAttribute("priceCategory", priceCategory);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("current", "restaurants");

        return "restaurant/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        restaurantService.incrementViewCount(id);
        Optional<RestaurantDetailDto> maybeDto = restaurantService.findDetailById(id);
        model.addAttribute("restaurant", maybeDto.orElse(null));
        return "restaurant/show";
    }

    @GetMapping("/slug/{slug}")
    public String showBySlug(@PathVariable String slug, Model model) {
        Optional<RestaurantDetailDto> maybeDto = restaurantService.findDetailBySlug(slug);

        if (maybeDto.isEmpty()) {
            return "error/404";
        }

        model.addAttribute("restaurant", maybeDto.orElse(null));
        return "restaurant/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        model.addAttribute("palceId", restaurantService.findAll());
        return "restaurant/edit-create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute Restaurant restaurant,
            BindingResult bindingResult,
            Model model) {

        // Validazione form
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("edit", false);
            return "restaurant/edit-create";
        }

        // --- verifica se esiste già un restaurant con = placeId
        Optional<Restaurant> existing = restaurantService.findByPlaceId(restaurant.getPlaceId());
        if (existing.isPresent()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("edit", false);
            model.addAttribute("placeIdError", "Esiste già un ristorante con questo Place ID!");
            return "restaurant/edit-create";
        }

        restaurantService.save(restaurant);
        return "redirect:/admin/restaurants";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Optional<Restaurant> maybeExisting = restaurantService.getByIdOptionalRestaurant(id);

        if (maybeExisting.isEmpty()) {
            return "error/404";
        }
        Restaurant existing = maybeExisting.get();

        model.addAttribute("restaurant", existing);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("imgs", imgRestaurantService.findByRestaurantId(id));
        model.addAttribute("edit", true);
        return "restaurant/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String updateData(
            @PathVariable Long id,
            @Valid @ModelAttribute Restaurant restaurant,
            BindingResult bindingResult,
            Model model) {

        Optional<Restaurant> maybeExisting = restaurantService.getByIdOptionalRestaurant(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("imgs", imgRestaurantService.findByRestaurantId(id));
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("edit", true);
            return "restaurant/edit-create";
        }

        if (maybeExisting.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ristorante non trovato");
        }

        Restaurant existing = maybeExisting.get();

        // tutti i campi base modificabili
        existing.setName(restaurant.getName());
        existing.setAddress(restaurant.getAddress());
        existing.setCity(restaurant.getCity());
        existing.setPostalCode(restaurant.getPostalCode());
        existing.setProvince(restaurant.getProvince());
        existing.setCountry(restaurant.getCountry());
        existing.setMenuLink(restaurant.getMenuLink());
        existing.setPhoneNumber(restaurant.getPhoneNumber());
        existing.setMapsUrl(restaurant.getMapsUrl());
        existing.setWebsiteUrl(restaurant.getWebsiteUrl());
        existing.setBookingLink(restaurant.getBookingLink());
        existing.setCategories(restaurant.getCategories());
        existing.setIsPartner(restaurant.getIsPartner());
        // Puoi aggiungere altri campi se necessario

        restaurantService.update(existing);
        return "redirect:/admin/restaurants";
    }

    // Gestione immagini (aggiunta e/o rimozione)
    @PostMapping("/edit/{id}/images")
    public String updateImages(
            @PathVariable Long id,
            Model model,
            @RequestParam(value = "deleteImageIds", required = false) List<Long> deleteImageIds,
            @RequestParam(value = "newImages", required = false) MultipartFile[] newImages) {

        Restaurant restaurant = restaurantService.getByIdRestaurant(id);

        // Rimuovi immagini
        if (deleteImageIds != null && !deleteImageIds.isEmpty()) {
            for (Long imgId : deleteImageIds) {
                imgRestaurantService.findById(imgId).ifPresent(img -> {
                    imgRestaurantService.deleteById(imgId);
                    imageStorageService.delete(img.getPath());
                });
            }
            // Dopo la rimozione, aggiorna la lista immagini fresca dal DB!
            restaurant = restaurantService.getByIdRestaurant(id);
        }

        // Carica nuove immagini
        if (newImages != null && newImages.length > 0) {
            for (MultipartFile file : newImages) {
                if (file != null && !file.isEmpty()) {
                    String path = imageStorageService.store(file, restaurant.getId());
                    ImgRestaurant img = new ImgRestaurant();
                    img.setPath(path);
                    img.setRestaurant(restaurant);
                    imgRestaurantService.save(img);
                }
            }
        }

        // *** LOGICA PRIMARY IMAGE ***
        // Se la primaryImage è vuota O non esiste più tra le immagini
        boolean mustUpdatePrimary = false;
        String currentPrimary = restaurant.getPrimaryImage();
        List<ImgRestaurant> images = imgRestaurantService.findByRestaurantId(id);

        if (currentPrimary == null || currentPrimary.isBlank()) {
            mustUpdatePrimary = true;
        } else {
            boolean stillExists = images.stream()
                    .anyMatch(img -> img.getPath().equals(currentPrimary));
            if (!stillExists)
                mustUpdatePrimary = true;
        }

        if (mustUpdatePrimary) {
            if (!images.isEmpty()) {
                restaurant.setPrimaryImage(images.get(0).getPath());
            } else {
                restaurant.setPrimaryImage(null);
            }
            restaurantService.update(restaurant);
        }
        return "redirect:/admin/restaurants";
    }

    @PostMapping("/pause/{id}")
    public String pause(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getByIdRestaurant(id);
        if (restaurant != null) {
            restaurant.setIsVisible(false);
            restaurantService.update(restaurant);
        }
        return "redirect:/admin/restaurants";
    }

    @PostMapping("/restart/{id}")
    public String restart(@PathVariable Long id) {
        Restaurant r = restaurantService.getByIdRestaurant(id);
        if (r != null) {
            r.setIsVisible(true);
            restaurantService.update(r);
        }
        return "redirect:/admin/restaurants";
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getByIdRestaurant(id);

        if (restaurant == null) {
            logger.warn("[DELETE] Nessun ristorante trovato con id={}", id);
            return "redirect:/admin/restaurants";
        }

        // 1. Copia le immagini PRIMA di modificare
        List<ImgRestaurant> imagesCopy = List.copyOf(restaurant.getImages());

        // 2. Scollega le categorie
        restaurant.getCategories().clear();
        restaurantService.update(restaurant);

        // 3. Elimina i file fisici PRIMA di eliminare il ristorante (ma NON toccare
        // DB!)
        for (ImgRestaurant img : imagesCopy) {
            try {
                logger.info("[DELETE IMG] id={}, path={}", img.getId(), img.getPath());
                imageStorageService.delete(img.getPath());
            } catch (Exception e) {
                logger.error("[DELETE IMG] Errore eliminando file: {} - {}", img.getPath(), e.getMessage());
            }
        }

        // 4. Elimina il restaurant, Hibernate nel DB
        try {
            restaurantService.deleteById(id);
            logger.info("[DELETE] Ristorante eliminato con successo: id={}", id);
        } catch (Exception e) {
            logger.error("[DELETE] Errore nell'eliminazione del ristorante id={}: {}", id, e.getMessage());
        }

        return "redirect:/admin/restaurants";
    }

    @GetMapping("/most-viewed")
    public String mostViewed(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "desc") String order) {

        Page<RestaurantSummaryDto> restaurantPage = restaurantService.findMostViewedPaged(page, size, order);
        model.addAttribute("restaurantPage", restaurantPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", restaurantPage.getTotalPages());
        model.addAttribute("order", order);
        model.addAttribute("current", "mostViewed");

        return "stats/most-viewed";
    }

}

// @GetMapping("/most-viewed")
// public String mostViewed(Model model,
// @RequestParam(defaultValue = "0") int page,
// @RequestParam(defaultValue = "10") int size) {
// Page<RestaurantSummaryDto> restaurantPage =
// restaurantService.findMostViewedPaged(page, size);
// model.addAttribute("restaurantPage", restaurantPage);
// model.addAttribute("currentPage", page);
// model.addAttribute("totalPages", restaurantPage.getTotalPages());
// return "stats/most-viewed";
// }

// @PostMapping("/edit/{id}")
// public String update(
// @PathVariable Long id,
// @Valid @ModelAttribute Restaurant restaurant,
// BindingResult bindingResult,
// Model model,
// @RequestParam(value = "deleteImageIds", required = false) List<Long>
// deleteImageIds,
// @RequestParam(value = "newImages", required = false) MultipartFile[]
// newImages) {
// Restaurant existing = restaurantService.getByIdRestaurant(id);

// if (bindingResult.hasErrors()) {
// model.addAttribute("imgs", imgRestaurantService.findByRestaurantId(id));
// model.addAttribute("edit", true);
// return "restaurant/edit-create";
// }

// // 1) elimina fisicamente e dal DB le immagini selezionate
// if (deleteImageIds != null) {
// for (Long imgId : deleteImageIds) {
// imgRestaurantService.findById(imgId).ifPresent(img -> {
// // imageStorageService.delete(img.getPath());
// imgRestaurantService.deleteById(imgId);
// imageStorageService.delete(img.getPath());

// });
// }
// }

// // 2) salva i nuovi upload
// if (newImages != null && newImages.length > 0) {
// for (MultipartFile file : newImages) {
// if (!file.isEmpty()) {
// String path = imageStorageService.store(file, existing.getId());
// ImgRestaurant img = new ImgRestaurant();
// img.setPath(path);
// img.setRestaurant(existing);
// imgRestaurantService.save(img);
// }
// }
// }

// // 3) aggiorna solo i campi base di existing che sono effettivamente nel form
// // existing.setName(restaurant.getName());
// // existing.setAddress(restaurant.getAddress());
// // existing.setCity(restaurant.getCity());
// // existing.setPostalCode(restaurant.getPostalCode());
// // existing.setProvince(restaurant.getProvince());
// // existing.setCountry(restaurant.getCountry());
// // existing.setLatitude(restaurant.getLatitude());
// // existing.setLongitude(restaurant.getLongitude());
// // existing.setMenuLink(restaurant.getMenuLink());
// // existing.setPhoneNumber(restaurant.getPhoneNumber());
// // existing.setPriceCategory(restaurant.getPriceCategory());
// // existing.setIsVisible(restaurant.getIsVisible());
// // existing.setCategories(restaurant.getCategories());
// // NON toccare existing.setImages(), rating, reviewCount, googleRank,
// mapsUrl,
// // websiteUrl, plateformClientId, bookingLink, placeId, primaryImage

// restaurantService.update(existing);

// return "redirect:/restaurants";
// }