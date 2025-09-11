package com.spring.restfinder.rest_finder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.restfinder.rest_finder.dto.categoryDto.CategoryDTO;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /** Restituisce tutti i ristoranti */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /** Cerca un ristorante per id */
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category getById(Long id) {
        Optional<Category> categoryAttempt = categoryRepository.findById(id);
        return categoryAttempt.get();
    }

    /** Salva o aggiorna un ristorante */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /** Elimina un ristorante */
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public void delete(Long id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

    public boolean isVisible(Long id) {
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            return categoryOpt.get().getIsVisible();
        }
        return false;
    }

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getItName() );
    }

 

}