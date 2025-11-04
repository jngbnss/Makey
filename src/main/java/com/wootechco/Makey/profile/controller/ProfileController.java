package com.wootechco.Makey.profile.controller;

import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import com.wootechco.Makey.profile.service.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "http://localhost:5173") // React dev server
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ProfileResponseDto getProfile(@PathVariable Long id) {
        return service.getProfile(id);
    }

    @PutMapping("/{id}")
    public ProfileResponseDto updateProfile(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto) {
        return service.updateProfile(id, requestDto);
    }
}
