package com.wootechco.Makey.profile.service;

import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import com.wootechco.Makey.profile.entity.Profile;
import com.wootechco.Makey.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    // 전체 프로필 조회
    public List<ProfileResponseDto> getAllProfiles() {
        return repository.findAll()
                .stream()
                .map(ProfileResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 프로필 조회
    public ProfileResponseDto getProfile(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 존재하지 않습니다. id=" + id));
        return new ProfileResponseDto(profile);
    }

    // 프로필 생성
    public ProfileResponseDto createProfile(ProfileRequestDto requestDto) {
        Profile profile = new Profile(requestDto.getName(), requestDto.getEmail());
        Profile saved = repository.save(profile);
        return new ProfileResponseDto(saved);
    }

    // 프로필 수정
    public ProfileResponseDto updateProfile(Long id, ProfileRequestDto requestDto) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 존재하지 않습니다. id=" + id));
        profile.setName(requestDto.getName());
        profile.setEmail(requestDto.getEmail());
        // 필요시 다른 필드 업데이트
        return new ProfileResponseDto(profile);
    }

    // 프로필 삭제
    public void deleteProfile(Long id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("프로필이 존재하지 않습니다. id=" + id));
        repository.delete(profile);
    }
}
