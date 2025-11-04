package com.wootechco.Makey.profile.service;

import com.wootechco.Makey.profile.dto.ProfileResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional  // 테스트 후 DB 롤백
class ProfileServiceIntegrationTest {

    @Autowired
    private ProfileService profileService;

    @Test
    void testProfilesFromInitDb() {
        // 에렌 예거 프로필 검증
        ProfileResponseDto eren = profileService.getProfile(1L);
        assertEquals("에렌 예거", eren.getUsername());
        assertEquals("eren@example.com", eren.getEmail());
        assertEquals("프론트엔드 개발자입니다.", eren.getBio());

        // 미카사 아커만 프로필 검증
        ProfileResponseDto mikasa = profileService.getProfile(2L);
        assertEquals("미카사 아커만", mikasa.getUsername());
        assertEquals("mikasa@example.com", mikasa.getEmail());
        assertEquals("백엔드 개발자입니다.", mikasa.getBio());

        // 아르민 알레르토 프로필 검증
        ProfileResponseDto armin = profileService.getProfile(3L);
        assertEquals("아르민 알레르토", armin.getUsername());
        assertEquals("armin@example.com", armin.getEmail());
        assertEquals("모바일 앱 개발자입니다.", armin.getBio());
    }
}
