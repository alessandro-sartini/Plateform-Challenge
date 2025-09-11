package com.spring.restfinder.rest_finder.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.restfinder.rest_finder.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findByName(String name);


}
