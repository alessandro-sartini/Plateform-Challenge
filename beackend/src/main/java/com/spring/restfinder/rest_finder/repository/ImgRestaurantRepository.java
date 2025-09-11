package com.spring.restfinder.rest_finder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.restfinder.rest_finder.model.ImgRestaurant;

public interface ImgRestaurantRepository extends JpaRepository<ImgRestaurant, Long> {
    /**
     * Recupera tutte le immagini.
     */
    List<ImgRestaurant> findAll();

    /**
     * Recupera un'immagine per ID.
     */
    Optional<ImgRestaurant> findById(Long id);

    /**
     * Salva una nuova immagine o aggiorna quella esistente.
     */
    ImgRestaurant save(ImgRestaurant imgRestaurant);

    /**
     * Elimina un'immagine per ID.
     */
    void deleteById(Long id);

    /**
     * Recupera tutte le immagini associate a un ristorante.
     */
    List<ImgRestaurant> findByRestaurantId(Long restaurantId);
}
