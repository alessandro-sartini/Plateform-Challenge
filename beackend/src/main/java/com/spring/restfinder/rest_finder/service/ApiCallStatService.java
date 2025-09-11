package com.spring.restfinder.rest_finder.service;

import com.spring.restfinder.rest_finder.model.ApiCallStat;
import com.spring.restfinder.rest_finder.repository.ApiCallStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApiCallStatService {

    @Autowired
    private ApiCallStatRepository repo;

    public void incrementEndpoint(String endpoint) {
        LocalDate today = LocalDate.now();
        ApiCallStat stat = repo.findByEndpointAndDate(endpoint, today)
                .orElseGet(() -> {
                    ApiCallStat s = new ApiCallStat();
                    s.setEndpoint(endpoint);
                    s.setDate(today);
                    s.setCount(0);
                    return s;
                });
        stat.setCount(stat.getCount() + 1);
        repo.save(stat);
    }

    // Opzionale: metodo per ottenere il numero di chiamate
    public int getCountForEndpoint(String endpoint, LocalDate date) {
        return repo.findByEndpointAndDate(endpoint, date)
                .map(ApiCallStat::getCount)
                .orElse(0);
    }

    public List<ApiCallStat> getAllStats() {
       List<ApiCallStat> listReport= repo.findAllByOrderByDateDescEndpointAsc();
       return listReport;
    }
}
