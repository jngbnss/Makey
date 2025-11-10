package com.wootechco.Makey.elderly.controller;

import com.wootechco.Makey.elderly.dto.LocationRequestDto;
import com.wootechco.Makey.elderly.dto.LocationResponseDto;
import com.wootechco.Makey.elderly.dto.PersonRequestDto;
import com.wootechco.Makey.elderly.dto.PersonResponseDto;
import com.wootechco.Makey.elderly.service.PersonService;
import jakarta.annotation.PostConstruct;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PersonController {

    private final PersonService service;

    // 랜덤 위치 생성 (위도, 경도)
    private double generateRandomLatitude() {
        Random random = new Random();
        // 예시: 서울을 중심으로 0.01도 내외로 랜덤하게 생성
        return 37.5665 + (random.nextDouble() * 0.02 - 0.01);
    }

    private double generateRandomLongitude() {
        Random random = new Random();
        // 예시: 서울을 중심으로 0.02도 내외로 랜덤하게 생성
        return 126.9780 + (random.nextDouble() * 0.02 - 0.01);
    }


    /**
     * 애플리케이션 시작 시 초기 데이터를 생성합니다.
     */
    @PostConstruct
    public void initData() {
        // 초기 데이터 생성
        PersonRequestDto person1 = PersonRequestDto.builder()
                .name("김노인")
                .age(78)
                .dementiaLevel("경증")
                .guardianName("최보호자")
                .guardianPhone("010-1234-5678")
                .latitude(37.5665)
                .longitude(126.9780)
                .build();

        PersonRequestDto person2 = PersonRequestDto.builder()
                .name("이노인")
                .age(82)
                .dementiaLevel("중증")
                .guardianName("정보호자")
                .guardianPhone("010-8765-4321")
                .latitude(37.5796)
                .longitude(126.9770)
                .build();

        PersonRequestDto person3 = PersonRequestDto.builder()
                .name("박노인")
                .age(85)
                .dementiaLevel("초기")
                .guardianName("강보호자")
                .guardianPhone("010-5555-7777")
                .latitude(37.5830)
                .longitude(126.9800)
                .build();

        // 데이터가 없을 경우만 저장
        if (service.findAll().isEmpty()) {
            service.create(person1);
            service.create(person2);
            service.create(person3);
        }
    }

    // Person 생성
    @PostMapping
    public ResponseEntity<PersonResponseDto> create(@RequestBody PersonRequestDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // 전체 Person 조회
    @GetMapping
    public ResponseEntity<List<PersonResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // ID로 Person 조회
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // Person 정보 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDto> update(
            @PathVariable Long id, @RequestBody PersonRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // Person 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 위치 업데이트 (랜덤 위치)
    @PutMapping("/{id}/updateRandomLocation")
    public ResponseEntity<LocationResponseDto> updateRandomLocation(@PathVariable Long id) {
        // 랜덤 위치 생성
        double randomLatitude = generateRandomLatitude();
        double randomLongitude = generateRandomLongitude();

        // LocationRequestDto에 랜덤 위치 저장
        LocationRequestDto locationRequestDto = LocationRequestDto.builder()
                .latitude(randomLatitude)
                .longitude(randomLongitude)
                .build();

        // 위치 업데이트
        LocationResponseDto updatedLocation = service.updateLocation(id, locationRequestDto);

        if (updatedLocation == null) {
            return ResponseEntity.status(404).build(); // Person이 없을 경우
        }

        return ResponseEntity.ok(updatedLocation); // 위치 업데이트된 정보 반환
    }

    // 위치 업데이트
    @PutMapping("/{id}/updateLocation")
    public ResponseEntity<LocationResponseDto> updateLocation(
            @PathVariable Long id,
            @RequestBody LocationRequestDto locationRequestDto) {

        LocationResponseDto updatedLocation = service.updateLocation(id, locationRequestDto);

        if (updatedLocation == null) {
            return ResponseEntity.status(404).build(); // Person이 없을 경우
        }

        return ResponseEntity.ok(updatedLocation); // 위치 업데이트된 정보 반환
    }
}
