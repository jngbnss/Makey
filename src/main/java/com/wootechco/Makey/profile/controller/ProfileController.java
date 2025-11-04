package com.wootechco.Makey.profile.controller;

import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import com.wootechco.Makey.profile.service.ProfileService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    // 전체 프로필 조회
    @GetMapping
    public List<ProfileResponseDto> getAllProfiles() {
        return service.getAllProfiles();
    }

    // 특정 프로필 조회
    @GetMapping("/{id}")
    public ProfileResponseDto getProfile(@PathVariable Long id) {
        return service.getProfile(id);
    }

    // 프로필 생성
    @PostMapping
    public ProfileResponseDto createProfile(@RequestBody ProfileRequestDto requestDto) {
        return service.createProfile(requestDto);
    }

    // 프로필 수정
    @PutMapping("/{id}")
    public ProfileResponseDto updateProfile(@PathVariable Long id, @RequestBody ProfileRequestDto requestDto) {
        return service.updateProfile(id, requestDto);
    }

    // 프로필 삭제
    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        service.deleteProfile(id);
    }
}
