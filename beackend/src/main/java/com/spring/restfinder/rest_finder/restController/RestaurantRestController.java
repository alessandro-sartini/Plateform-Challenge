package com.spring.restfinder.rest_finder.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.restfinder.rest_finder.dto.card.RestaurantSummaryDto;
import com.spring.restfinder.rest_finder.dto.details.ApiRestaurantDetailDto;
import com.spring.restfinder.rest_finder.service.ApiCallStatService;
import com.spring.restfinder.rest_finder.service.RestaurantService;

import org.springframework.hateoas.PagedModel;
// import org.springframework.hateoas.server.PagedResourcesAssembler;

@RestController
@CrossOrigin
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ApiCallStatService apiCallStatService;

    @Autowired
    private PagedResourcesAssembler<RestaurantSummaryDto> pagedResourcesAssembler;

    @GetMapping("/paged")
    public ResponseEntity<PagedModel<EntityModel<RestaurantSummaryDto>>> pagedList1(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search, 
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Integer priceCategory) {
        apiCallStatService.incrementEndpoint("/paged");
        Page<RestaurantSummaryDto> restaurantPage = restaurantService.searchPagedRest(
                search, page, size, category, minRating, priceCategory);
        PagedModel<EntityModel<RestaurantSummaryDto>> pagedModel = pagedResourcesAssembler.toModel(restaurantPage);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiRestaurantDetailDto> getBySlug(@PathVariable String slug) {
        // Incrementazioni!
        apiCallStatService.incrementEndpoint("/restaurants/slug/{slug}");
        restaurantService.incrementViewCount(slug);

        return restaurantService.findBySlug(slug)
                .map(ApiRestaurantDetailDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/most-viewed")
    public ResponseEntity<Page<RestaurantSummaryDto>> mostViewedPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        apiCallStatService.incrementEndpoint("/most-viewed");

        Page<RestaurantSummaryDto> pageRes = restaurantService.findMostViewedPaged(page, size);
        return ResponseEntity.ok(pageRes);
    }

}
// // SOLO RISTORANTI VISIBILI, paginato con HATEOAS
// @GetMapping("/paged/{page}")
// public ResponseEntity<PagedModel<EntityModel<RestaurantSummaryDto>>>
// pagedList(
// @PathVariable int page,
// @RequestParam(required = false, defaultValue = "10") int size,
// @RequestParam(required = false) String city,
// @RequestParam(required = false) String name,
// @RequestParam(required = false) Long categoryId,
// @RequestParam(required = false) Float minRating,
// @RequestParam(required = false) Integer priceCategory) {
// apiCallStatService.incrementEndpoint("/paged/{page}");
// Page<RestaurantSummaryDto> restaurantPage =
// restaurantService.searchPagedRest(
// name, city, page, size, categoryId, minRating, priceCategory);

// // Genera un PageRequest a partire da page e size (se già non usi Pageable)
// // Pageable pageable = PageRequest.of(page, size);

// // Ritorna la struttura HATEOAS
// PagedModel<EntityModel<RestaurantSummaryDto>> pagedModel =
// pagedResourcesAssembler.toModel(restaurantPage);

// return ResponseEntity.ok(pagedModel);
// }

// Endpoint paginato standard (anche con isVisible)
// @GetMapping("/paged/{page}")
// public ResponseEntity<Page<RestaurantSummaryDto>> pagedList(
// @PathVariable int page,
// @RequestParam(required = false, defaultValue = "10") int size,
// @RequestParam(required = false) String city,
// @RequestParam(required = false) String name,
// @RequestParam(required = false) Long categoryId,
// @RequestParam(required = false) Boolean isVisible,
// @RequestParam(required = false) Float minRating,
// @RequestParam(required = false) Integer priceCategory
// ) {
// apiCallStatService.incrementEndpoint("/paged/{page}");
// Page<RestaurantSummaryDto> restaurantPage = restaurantService.searchPaged(
// name, city, page, size, categoryId, isVisible, minRating, priceCategory
// );
// return ResponseEntity.ok(restaurantPage);
// }
