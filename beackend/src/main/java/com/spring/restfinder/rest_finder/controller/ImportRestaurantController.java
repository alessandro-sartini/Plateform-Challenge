package com.spring.restfinder.rest_finder.controller;

import com.spring.restfinder.rest_finder.dto.restaurantGoogle.Result;
import com.spring.restfinder.rest_finder.model.Restaurant;
import com.spring.restfinder.rest_finder.exception.RestaurantAlreadyExistsException;
import com.spring.restfinder.rest_finder.service.GooglePlacesService;
import com.spring.restfinder.rest_finder.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin/google/restaurants")
public class ImportRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private GooglePlacesService googlePlacesService;

    @GetMapping
    public String mostraRistoranti(
            @RequestParam(value = "citta", required = false, defaultValue = "milano") String citta,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pagetoken", required = false) String pagetoken,
            Model model) {

        Map<String, Object> resultMap;

        if (pagetoken != null && !pagetoken.isBlank()) {
            // Se hai solo nome, cerca col nome
            if (nome != null && !nome.isBlank()) {
                resultMap = googlePlacesService.cercaRisultatiConToken(nome, pagetoken);
            } else {
                resultMap = googlePlacesService.cercaRisultatiConToken(citta, pagetoken);
            }
        } else if (nome != null && !nome.isBlank()) {
            String query = nome;
            if (citta != null && !citta.isBlank()) {
                query = nome + " " + citta;
            }
            resultMap = googlePlacesService.cercaRisultatiConToken(query, null);
        } else {
            resultMap = googlePlacesService.cercaRisultatiConToken(citta, null);
        }

        List<Result> ristoranti = (List<Result>) resultMap.get("results");
        String nextPage = (String) resultMap.get("next_page_token");

        List<String> importedPlaceIds = restaurantService.findAll()
                .stream()
                .map(Restaurant::getPlaceId)
                .filter(Objects::nonNull)
                .toList();

        model.addAttribute("ristorantiGoogle", ristoranti);
        model.addAttribute("importedPlaceIds", importedPlaceIds);
        model.addAttribute("citta", citta);
        model.addAttribute("nome", nome);
        model.addAttribute("pagetoken", nextPage);
        model.addAttribute("current", "import");

        return "restaurantPlaces/indexMultiply";
    }

    @PostMapping("/salva-multipli")
    public String salvaRistorantiMultipli(
            @RequestParam(value = "selectedIndices", required = false) List<Integer> selectedIndices,
            @RequestParam(value = "citta", required = false, defaultValue = "milano") String citta,
            @RequestParam(value = "pagetoken", required = false) String pagetoken,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        if (selectedIndices != null) {
            for (Integer idx : selectedIndices) {
                String prefix = "ristoranti[" + idx + "].";
                String placeId = request.getParameter(prefix + "placeId");
                try {
                    restaurantService.saveFromGooglePlaceId(placeId);
                } catch (RestaurantAlreadyExistsException ex) {
                    redirectAttributes.addFlashAttribute("error", ex.getMessage());
                }
            }
        }
        redirectAttributes.addAttribute("citta", citta);
        if (pagetoken != null && !pagetoken.isBlank()) {
            redirectAttributes.addAttribute("pagetoken", pagetoken);
        }
        return "redirect:/admin/google/restaurants";
    }
}
