package com.wootechco.Makey.elderly.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LocationRequestDto {
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
}
