package com.wootechco.Makey.elderly.controller;

import com.wootechco.Makey.elderly.service.GeocodingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodingController {

    private final GeocodingService geocodingService;

    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @GetMapping("/api/geocode")
    public double[] getCoordinates(@RequestParam String address) {
        return geocodingService.getLatLng(address);
    }
}
