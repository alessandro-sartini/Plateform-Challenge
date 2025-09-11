package com.spring.restfinder.rest_finder.exception;

public class RestaurantAlreadyExistsException extends RuntimeException {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
