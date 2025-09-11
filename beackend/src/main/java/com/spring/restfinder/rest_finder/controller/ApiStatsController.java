package com.spring.restfinder.rest_finder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.restfinder.rest_finder.model.ApiCallStat;
import com.spring.restfinder.rest_finder.service.ApiCallStatService;

@Controller
@RequestMapping("/admin/stats")
public class ApiStatsController {
    @Autowired
    private ApiCallStatService statService;

    @GetMapping
    public String stats(Model model) {
        List<ApiCallStat> stats = statService.getAllStats();
        model.addAttribute("current", "stats");

        model.addAttribute("stats", stats);
        return "stats/index";
    }
}