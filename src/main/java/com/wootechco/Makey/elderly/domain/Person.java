package com.wootechco.Makey.elderly.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Integer age;
    private String dementiaLevel;
    private String guardianName;
    private String guardianPhone;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime lastSeenAt;
    private String lastSeenPlace;

    // ✅ 현재 실시간 GPS 위치 (추가)
    private Double latitude;       // 현재 위도
    private Double longitude;      // 현재 경도
    private LocalDateTime lastUpdated; // 마지막 갱신 시각

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationHistory> locationHistoryList = new ArrayList<>();

    public enum Status { MISSING, FOUND }
}
