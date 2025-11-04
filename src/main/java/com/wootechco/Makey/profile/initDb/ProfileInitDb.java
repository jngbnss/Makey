package com.wootechco.Makey.profile.initDb;

import com.wootechco.Makey.profile.entity.Profile;
import com.wootechco.Makey.profile.repository.ProfileRepository;
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

        private final ProfileRepository repository;

        public void dbInit() {
            repository.save(new Profile("에렌 예거", "eren@example.com"));
            repository.save(new Profile("미카사 아커만", "mikasa@example.com"));
            repository.save(new Profile("아르민 알레르토", "armin@example.com"));
        }
    }
}
