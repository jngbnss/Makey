package com.wootechco.Makey.profile.service;

import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import com.wootechco.Makey.profile.entity.Profile;
import com.wootechco.Makey.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @Mock
    private ProfileRepository repository;

    @InjectMocks
    private ProfileService profileService;

    private Profile profile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profile = new Profile("originalUser", "original@example.com");
    }

    @Test
    void getProfile_ShouldReturnProfileResponseDto_WhenProfileExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(profile));

        ProfileResponseDto response = profileService.getProfile(1L);

        assertEquals("originalUser", response.getName());
        assertEquals("original@example.com", response.getEmail());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getProfile_ShouldThrowException_WhenProfileDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> profileService.getProfile(1L));
        assertEquals("프로필이 존재하지 않습니다. id=1", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void updateProfile_ShouldUpdateAndReturnProfileResponseDto_WhenProfileExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(profile));
        ProfileRequestDto requestDto = new ProfileRequestDto("newUser", "new@example.com");

        ProfileResponseDto response = profileService.updateProfile(1L, requestDto);

        assertEquals("newUser", response.getName());
        assertEquals("new@example.com", response.getEmail());

        // Repository findById 호출 검증
        verify(repository, times(1)).findById(1L);
        // 프로필 객체가 실제로 업데이트 되었는지도 검증
        assertEquals("newUser", profile.getName());
        assertEquals("new@example.com", profile.getEmail());
    }

    @Test
    void updateProfile_ShouldThrowException_WhenProfileDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        ProfileRequestDto requestDto = new ProfileRequestDto("newUser", "new@example.com");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> profileService.updateProfile(1L, requestDto));
        assertEquals("프로필이 존재하지 않습니다. id=1", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }
}
