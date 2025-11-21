package com.wootechco.Makey.elderly.dto;

import com.wootechco.Makey.elderly.domain.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String dementiaLevel;
    private String guardianName;
    private String guardianPhone;
    private String lastSeenPlace;
    private Double latitude;
    private Double longitude;

    // Person 엔티티를 DTO로 변환하는 생성자 추가
    public PersonResponseDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
        this.dementiaLevel = person.getDementiaLevel();
        this.guardianName = person.getGuardianName();
        this.guardianPhone = person.getGuardianPhone();
        this.lastSeenPlace = person.getLastSeenPlace();
        this.latitude = person.getLatitude();
        this.longitude = person.getLongitude();
    }
}
