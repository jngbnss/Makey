package com.wootechco.Makey.gps.controller;

import com.wootechco.Makey.gps.service.GPSService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gps")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class GPSController {

    private final GPSService gpsService;

    @PostMapping("/update/{personId}")
    public String updateLocation(@PathVariable Long personId, @RequestParam String deviceId) {
        gpsService.updatePersonLocation(personId, deviceId);
        return "GPS 데이터 업데이트 완료";
    }
}
