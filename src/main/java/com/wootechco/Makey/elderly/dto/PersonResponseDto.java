package com.wootechco.Makey.elderly.dto;

import com.wootechco.Makey.elderly.domain.Person;
import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PersonResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String dementiaLevel;
    private String guardianName;
    private String guardianPhone;
    private String lastSeenPlace;
    private String status;
    private List<LocationResponseDto> locations;

    public static PersonResponseDto fromEntity(Person person) {
        return PersonResponseDto.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .dementiaLevel(person.getDementiaLevel())
                .guardianName(person.getGuardianName())
                .guardianPhone(person.getGuardianPhone())
                .lastSeenPlace(person.getLastSeenPlace())
                .status(person.getStatus().name())
                .locations(person.getLocationHistoryList()
                        .stream()
                        .map(LocationResponseDto::fromEntity)
                        .toList())
                .build();
    }
}
