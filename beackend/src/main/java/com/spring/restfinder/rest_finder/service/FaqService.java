package com.spring.restfinder.rest_finder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.restfinder.rest_finder.model.Faq;
import com.spring.restfinder.rest_finder.repository.FaqRepository;

@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    public List<Faq> findAll() {
        return faqRepository.findAll();
    }

    // public Optional<Faq> findById(Long id) {
    //     return faqRepository.findById(id).get();
    // }

    /** Cerca un ristorante per id */
    public Optional<Faq> findById(Long id) {
        return faqRepository.findById(id);
    }

    public Faq getById(Long id) {
        Optional<Faq> FaqAttempt = faqRepository.findById(id);
        return FaqAttempt.get();
    }

    /** Salva o aggiorna un ristorante */
    public Faq save(Faq Faq) {
        return faqRepository.save(Faq);
    }

    /** Elimina un ristorante */
    public void deleteById(Long id) {
        faqRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return faqRepository.existsById(id);
    }

    public void delete(Long id) {
        Faq Faq = getById(id);
        faqRepository.delete(Faq);
    }

}
