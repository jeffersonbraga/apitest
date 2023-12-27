package com.texo.apitest.controller;

import com.texo.apitest.dto.SearchAwardsIntervalDTO;
import com.texo.apitest.services.ProducerAwardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MoviesController {

    private final ProducerAwardService producerAwardService;

    public MoviesController(ProducerAwardService producerAwardService) {
        this.producerAwardService = producerAwardService;
    }

    @GetMapping("awards/interval")
    public SearchAwardsIntervalDTO getData() {
        return this.producerAwardService.awardIntervalMaxMin();
    }
}
