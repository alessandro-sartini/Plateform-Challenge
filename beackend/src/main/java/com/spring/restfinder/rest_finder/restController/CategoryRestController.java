package com.spring.restfinder.rest_finder.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restfinder.rest_finder.dto.categoryDto.CategoryDTO;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.service.ApiCallStatService;
import com.spring.restfinder.rest_finder.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApiCallStatService apiCallStatService;

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> index() {

        apiCallStatService.incrementEndpoint("/category");
        List<Category> categoriesAttempt = categoryService.findAll();
        if (categoriesAttempt.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoryDTO> categoriesDto = new ArrayList<>();

        for (Category category : categoriesAttempt) {
            if (category.getIsVisible()) {
             categoriesDto.add(categoryService.toDTO(category));
            }
        }

        return ResponseEntity.ok(categoriesDto);
    }

}
