package com.wootechco.Makey.elderly.dto;

import com.wootechco.Makey.elderly.domain.LocationHistory;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LocationResponseDto {
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;

    public static LocationResponseDto fromEntity(LocationHistory entity) {
        return LocationResponseDto.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .timestamp(entity.getTimestamp())
                .build();
    }
}
