package com.spring.restfinder.rest_finder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.spring.restfinder.rest_finder.model.Category;
import com.spring.restfinder.rest_finder.model.Restaurant;
import com.spring.restfinder.rest_finder.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // @GetMapping
    // public String index(Model model) {
    // model.addAttribute("categories", categoryService.findAll());
    // model.addAttribute("current", "categories");
    // return "category/index";
    // }
@GetMapping
public String categoriePage(
    @RequestParam(defaultValue = "it") String lang,
    Model model) {
    List<Category> categories = categoryService.findAll();
    model.addAttribute("categories", categories);
    model.addAttribute("lang", lang);
    // Passa anche la URL della pagina, ti servirà nel template
    model.addAttribute("currentUrl", "/admin/categories");
    return "category/index";
}


    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/edit-create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute Category category,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/edit-create";
        }
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        Optional<Category> maybeExisting = categoryService.findById(id);
        if (maybeExisting.isEmpty()) {
            return "error/404";
        }
        Category existing = maybeExisting.get();
        model.addAttribute("category", existing);
        model.addAttribute("edit", true);
        return "category/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String updateData(@PathVariable Long id, @Valid @ModelAttribute Category category,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "category/edit-create";
        }
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        Category catToDelete = categoryService.getById(id);

        for (Restaurant restaurant : catToDelete.getRestaurants()) {
            restaurant.getCategories().remove(catToDelete);
        }

        categoryService.delete(id);
        return "redirect:/admin/categories";
    }

    @PostMapping("/isVisible/{id}")
    public String isVisible(@PathVariable Long id) {

        Category catToVerify = categoryService.getById(id);
        catToVerify.setIsVisible(!catToVerify.getIsVisible());
        categoryService.save(catToVerify);
        return "redirect:/admin/categories";
    }

}
