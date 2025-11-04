package com.wootechco.Makey.profile.service;

import com.wootechco.Makey.profile.domain.Profile;
import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import com.wootechco.Makey.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ProfileResponseDto getProfile(Long id) {
        Profile profile = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Profile not found")
        );
        return new ProfileResponseDto(profile.getUsername(), profile.getEmail(), profile.getBio());
    }

    @Transactional
    public ProfileResponseDto updateProfile(Long id, ProfileRequestDto requestDto) {
        Profile profile = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Profile not found")
        );
        profile.setUsername(requestDto.getUsername());
        profile.setEmail(requestDto.getEmail());
        profile.setBio(requestDto.getBio());
        return new ProfileResponseDto(profile.getUsername(), profile.getEmail(), profile.getBio());
    }
}
