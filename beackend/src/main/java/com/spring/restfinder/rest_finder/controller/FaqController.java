package com.spring.restfinder.rest_finder.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.restfinder.rest_finder.model.Faq;
import com.spring.restfinder.rest_finder.service.FaqService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("admin/faqs")
public class FaqController {

    @Autowired
    private FaqService faqService;

    @GetMapping
    public String index(Model model) {

        List<Faq> faqs = faqService.findAll();

        model.addAttribute("current", "faqs");

        model.addAttribute("faqs", faqs);

        return "faq/index";
    }

    @GetMapping("/create")
    public String createFrom(Model model) {

        model.addAttribute("faq", new Faq());

        return "faq/edit-create";
    }

    @PostMapping("/create")
    public String create(Model model, @Valid @ModelAttribute Faq faq, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "faq/edit-create";
        }

        faqService.save(faq);
        return "redirect:/admin/faqs";
    }

    @GetMapping("/edit/{id}")
    public String editFrom(Model model, @PathVariable Long id) {


        Optional<Faq> maybeExisting = faqService.findById(id);

        if (maybeExisting.isEmpty()) {
            return "error/404";
        }
        Faq existing = maybeExisting.get();
        model.addAttribute("faq", existing);
        model.addAttribute("edit", true);
        return "faq/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String edit(Model model, @Valid @ModelAttribute Faq faq, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "faq/edit-create";
        }

        faqService.save(faq);
        return "redirect:/admin/faqs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        faqService.delete(id);
        return "redirect:/admin/faqs";
    }

}
