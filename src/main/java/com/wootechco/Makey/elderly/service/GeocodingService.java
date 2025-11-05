package com.wootechco.Makey.elderly.service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String apiKey;

    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double[] getLatLng(String address) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
                .queryParam("address", address)
                .queryParam("key", apiKey)
                .build()
                .toUri();

        Map<String, Object> response = restTemplate.getForObject(uri, Map.class);

        if (response == null || ((List<?>) response.get("results")).isEmpty()) {
            throw new IllegalArgumentException("주소가 잘못되었거나 결과가 없습니다.");
        }

        Map<String, Object> location = (Map<String, Object>) ((Map<?, ?>) ((List<?>) response.get("results")).get(0)).get("geometry");
        Map<String, Object> latLng = (Map<String, Object>) location.get("location");

        double lat = ((Number) latLng.get("lat")).doubleValue();
        double lng = ((Number) latLng.get("lng")).doubleValue();

        return new double[]{lat, lng};
    }
}