package com.spring.restfinder.rest_finder.repository;

import com.spring.restfinder.rest_finder.model.ApiCallStat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApiCallStatRepository extends JpaRepository<ApiCallStat, Long> {
    Optional<ApiCallStat> findByEndpointAndDate(String endpoint, LocalDate date);

    List<ApiCallStat> findAllByOrderByDateDescEndpointAsc();
}
