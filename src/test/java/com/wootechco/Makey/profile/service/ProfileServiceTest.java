package com.wootechco.Makey.profile.service;

import com.wootechco.Makey.profile.domain.Profile;
import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import com.wootechco.Makey.profile.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
        profile = new Profile();
        profile.setUsername("originalUser");
        profile.setEmail("original@example.com");
        profile.setBio("Original bio");
    }

    @Test
    void getProfile_ShouldReturnProfileResponseDto_WhenProfileExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(profile));

        ProfileResponseDto response = profileService.getProfile(1L);

        assertEquals("originalUser", response.getUsername());
        assertEquals("original@example.com", response.getEmail());
        assertEquals("Original bio", response.getBio());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getProfile_ShouldThrowException_WhenProfileDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> profileService.getProfile(1L));
        assertEquals("Profile not found", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void updateProfile_ShouldUpdateAndReturnProfileResponseDto_WhenProfileExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(profile));
        ProfileRequestDto requestDto = new ProfileRequestDto("newUser", "new@example.com", "New bio");

        ProfileResponseDto response = profileService.updateProfile(1L, requestDto);

        assertEquals("newUser", response.getUsername());
        assertEquals("new@example.com", response.getEmail());
        assertEquals("New bio", response.getBio());

        // Repository findById 호출 검증
        verify(repository, times(1)).findById(1L);
        // 프로필 객체가 실제로 업데이트 되었는지도 검증
        assertEquals("newUser", profile.getUsername());
        assertEquals("new@example.com", profile.getEmail());
        assertEquals("New bio", profile.getBio());
    }

    @Test
    void updateProfile_ShouldThrowException_WhenProfileDoesNotExist() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        ProfileRequestDto requestDto = new ProfileRequestDto("newUser", "new@example.com", "New bio");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> profileService.updateProfile(1L, requestDto));
        assertEquals("Profile not found", exception.getMessage());
        verify(repository, times(1)).findById(1L);
    }
}
