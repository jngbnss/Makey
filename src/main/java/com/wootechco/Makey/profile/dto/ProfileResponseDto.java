package com.wootechco.Makey.profile.dto;

import com.wootechco.Makey.profile.entity.Profile;
import lombok.Getter;

@Getter
public class ProfileResponseDto {
    private Long id;
    private String name;
    private String email;

    public ProfileResponseDto(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.email = profile.getEmail();
    }
}
