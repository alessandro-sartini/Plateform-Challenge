package com.spring.restfinder.rest_finder.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isVisible", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isVisible;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "Il nome della categoria non può essere vuoto")
    @Size(min = 3, max = 100, message = "Il nome della categoria non può superare i 100 caratteri ed inferiore a 3!")
    private String name;

    @Column(name = "itName", nullable = true, columnDefinition = "VARCHAR(255) DEFAULT 'Non tradotto'")
    private String itName = "Non tradotto";

    @JsonBackReference
    @ManyToMany(mappedBy = "categories")
    private List<Restaurant> restaurants = new ArrayList<>();

    // Getters e setters

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
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isIsVisible() {
        return this.isVisible;
    }

    public String getItName() {
        return (this.itName == null || this.itName.trim().isEmpty()) ? "Non tradotto" : this.itName;
    }

    public void setItName(String itName) {
        if (itName == null || itName.trim().isEmpty()) {
            this.itName = "Non tradotto";
        } else {
            this.itName = itName;
        }
    }

}
