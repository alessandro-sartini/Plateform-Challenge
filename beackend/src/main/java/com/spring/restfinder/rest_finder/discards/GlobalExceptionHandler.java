package com.spring.restfinder.rest_finder.discards;
// package com.spring.restfinder.rest_finder.exception;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// public class GlobalExceptionHandler {
//     @ControllerAdvice
//     public static class Handler {
//         @ExceptionHandler(RestaurantAlreadyExistsException.class)
//         @ResponseStatus(HttpStatus.CONFLICT)
//         public String handleRestaurantAlreadyExists(RestaurantAlreadyExistsException ex,
//                 RedirectAttributes redirectAttributes) {
//             redirectAttributes.addFlashAttribute("error", ex.getMessage());
//             return "redirect:/google/restaurants";
//         }
//     }
// }
