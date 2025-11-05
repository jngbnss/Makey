package com.wootechco.Makey.elderly.dto;

import com.wootechco.Makey.elderly.domain.Person.Status;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PersonRequestDto {
    private String name;
    private Integer age;
    private String dementiaLevel;
    private String guardianName;
    private String guardianPhone;
    private String lastSeenPlace;
    private Status status;
}
