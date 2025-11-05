package com.wootechco.Makey.elderly.controller;

import com.wootechco.Makey.elderly.dto.LocationRequestDto;
import com.wootechco.Makey.elderly.dto.LocationResponseDto;
import com.wootechco.Makey.elderly.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/persons/{personId}/locations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationResponseDto> addLocation(
            @PathVariable Long personId,
            @RequestBody LocationRequestDto dto
    ) {
        return ResponseEntity.ok(locationService.addLocation(personId, dto));
    }

    @GetMapping
    public ResponseEntity<List<LocationResponseDto>> getLocations(@PathVariable Long personId) {
        return ResponseEntity.ok(locationService.getLocations(personId));
    }
}
