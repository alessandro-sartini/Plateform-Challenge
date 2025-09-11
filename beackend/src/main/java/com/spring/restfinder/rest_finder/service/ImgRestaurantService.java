package com.spring.restfinder.rest_finder.service;

import com.spring.restfinder.rest_finder.model.ImgRestaurant;
import com.spring.restfinder.rest_finder.repository.ImgRestaurantRepository;
import com.spring.restfinder.rest_finder.service.ImgRestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImgRestaurantService {

    @Autowired
    private ImgRestaurantRepository repository;

    public List<ImgRestaurant> findAll() {
        return repository.findAll();
    }

    public Optional<ImgRestaurant> findById(Long id) {
        return repository.findById(id);
    }

    public ImgRestaurant save(ImgRestaurant imgRestaurant) {
        return repository.save(imgRestaurant);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<ImgRestaurant> findByRestaurantId(Long restaurantId) {
        return repository.findByRestaurantId(restaurantId);
    }

}
