package com.wootechco.Makey.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileRequestDto {
    private String name;
    private String email;

    public ProfileRequestDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
