package com.wootechco.Makey.elderly.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;  // 위치 정보가 속한 Person 객체

    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;  // 위치가 기록된 시간

    public LocationHistory(Person person, Double latitude, Double longitude, LocalDateTime timestamp) {
        this.person = person;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }
}
