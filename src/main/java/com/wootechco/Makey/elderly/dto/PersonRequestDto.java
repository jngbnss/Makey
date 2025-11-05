package com.wootechco.Makey.elderly.dto;

import com.wootechco.Makey.elderly.domain.Person.Status;
import lombok.*;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDto {
    private String name;
    private Integer age;
    private String dementiaLevel;
    private String guardianName;
    private String guardianPhone;
    private String lastSeenPlace; // optional
    private Status status;        // optional
    private Double latitude;      // 새로 추가
    private Double longitude;     // 새로 추가
}
