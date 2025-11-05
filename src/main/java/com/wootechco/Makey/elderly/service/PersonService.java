package com.wootechco.Makey.elderly.service;

import com.wootechco.Makey.elderly.domain.Person;
import com.wootechco.Makey.elderly.dto.PersonRequestDto;
import com.wootechco.Makey.elderly.dto.PersonResponseDto;
import com.wootechco.Makey.elderly.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public PersonResponseDto create(PersonRequestDto dto) {
        Person person = Person.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .dementiaLevel(dto.getDementiaLevel())
                .guardianName(dto.getGuardianName())
                .guardianPhone(dto.getGuardianPhone())
                .lastSeenPlace(dto.getLastSeenPlace())
                .status(dto.getStatus())
                .build();
        return PersonResponseDto.fromEntity(repository.save(person));
    }

    public List<PersonResponseDto> findAll() {
        return repository.findAll().stream().map(PersonResponseDto::fromEntity).toList();
    }

    public PersonResponseDto findById(Long id) {
        return repository.findById(id)
                .map(PersonResponseDto::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 인물입니다."));
    }

    public PersonResponseDto update(Long id, PersonRequestDto dto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 인물입니다."));

        person.setName(dto.getName());
        person.setAge(dto.getAge());
        person.setDementiaLevel(dto.getDementiaLevel());
        person.setGuardianName(dto.getGuardianName());
        person.setGuardianPhone(dto.getGuardianPhone());
        person.setLastSeenPlace(dto.getLastSeenPlace());
        person.setStatus(dto.getStatus());

        return PersonResponseDto.fromEntity(repository.save(person));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // ✅ 애플리케이션 시작 시 초기 데이터 세팅
    @PostConstruct
    public void initData() {
        if (repository.count() == 0) { // 이미 데이터가 없을 때만 추가
            repository.save(Person.builder()
                    .name("홍길동")
                    .age(75)
                    .dementiaLevel("중증")
                    .guardianName("박보호자")
                    .guardianPhone("010-1234-5678")
                    .lastSeenPlace("서울역")
                    .status(Person.Status.MISSING)
                    .latitude(37.556)  // 예시 GPS
                    .longitude(126.972)
                    .lastUpdated(LocalDateTime.now())
                    .build());

            repository.save(Person.builder()
                    .name("김철수")
                    .age(82)
                    .dementiaLevel("경증")
                    .guardianName("이보호자")
                    .guardianPhone("010-9876-5432")
                    .lastSeenPlace("강남역")
                    .status(Person.Status.FOUND)
                    .latitude(37.498)
                    .longitude(127.027)
                    .lastUpdated(LocalDateTime.now())
                    .build());

            // 원하면 추가 인물 더 넣을 수 있음
        }
    }
}
