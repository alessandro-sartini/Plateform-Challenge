package com.spring.restfinder.rest_finder.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restfinder.rest_finder.model.Faq;
import com.spring.restfinder.rest_finder.service.FaqService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/faqs")
public class FaqRestController {

    @Autowired
    private FaqService faqService;

    @GetMapping
    public ResponseEntity<List<Faq>> index() {

        List<Faq> faqOptional = faqService.findAll();
        if (faqOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(faqOptional, HttpStatus.OK);

    }

}
