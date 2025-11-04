package com.wootechco.Makey.profile.initDb;

import com.wootechco.Makey.profile.domain.Profile;
import com.wootechco.Makey.profile.dto.ProfileRequestDto;
import com.wootechco.Makey.profile.repository.ProfileRepository;
import com.wootechco.Makey.profile.service.ProfileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
@RequiredArgsConstructor
public class ProfileInitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final ProfileRepository repository; // 직접 Repository 사용

        public void dbInit() {
            repository.save(new Profile("에렌 예거", "eren@example.com", "프론트엔드 개발자입니다."));
            repository.save(new Profile("미카사 아커만", "mikasa@example.com", "백엔드 개발자입니다."));
            repository.save(new Profile("아르민 알레르토", "armin@example.com", "모바일 앱 개발자입니다."));
        }
    }
}
