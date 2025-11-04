package com.wootechco.Makey.profile.repository;

import com.wootechco.Makey.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
