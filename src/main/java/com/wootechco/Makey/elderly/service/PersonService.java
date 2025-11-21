package com.wootechco.Makey.elderly.service;

import com.wootechco.Makey.elderly.domain.LocationHistory;
import com.wootechco.Makey.elderly.domain.Person;
import com.wootechco.Makey.elderly.dto.LocationRequestDto;
import com.wootechco.Makey.elderly.dto.LocationResponseDto;
import com.wootechco.Makey.elderly.dto.PersonRequestDto;
import com.wootechco.Makey.elderly.dto.PersonResponseDto;
import com.wootechco.Makey.elderly.repository.PersonRepository;
import com.wootechco.Makey.elderly.repository.LocationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final LocationHistoryRepository locationHistoryRepository;

    // =================== CRUD ===================

    public PersonResponseDto create(PersonRequestDto dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setAge(dto.getAge());
        person.setDementiaLevel(dto.getDementiaLevel());
        person.setGuardianName(dto.getGuardianName());
        person.setGuardianPhone(dto.getGuardianPhone());

        Person savedPerson = personRepository.save(person);
        return new PersonResponseDto(savedPerson);
    }

    public List<PersonResponseDto> findAll() {
        return personRepository.findAll()
                .stream()
                .map(PersonResponseDto::new)
                .collect(Collectors.toList());
    }

    public PersonResponseDto findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        return new PersonResponseDto(person);
    }

    public PersonResponseDto update(Long id, PersonRequestDto dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        person.setName(dto.getName());
        person.setAge(dto.getAge());
        person.setDementiaLevel(dto.getDementiaLevel());
        person.setGuardianName(dto.getGuardianName());
        person.setGuardianPhone(dto.getGuardianPhone());

        Person updatedPerson = personRepository.save(person);
        return new PersonResponseDto(updatedPerson);
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    // =================== 위치 업데이트 ===================

    public LocationResponseDto updateLocation(Long id, LocationRequestDto locationRequestDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        person.setLatitude(locationRequestDto.getLatitude());
        person.setLongitude(locationRequestDto.getLongitude());
        person.setLastSeenPlace("위도: " + locationRequestDto.getLatitude() + ", 경도: " + locationRequestDto.getLongitude());

        LocalDateTime timestamp = locationRequestDto.getTimestamp() != null ? locationRequestDto.getTimestamp() : LocalDateTime.now();

        LocationHistory locationHistory = new LocationHistory(person, locationRequestDto.getLatitude(), locationRequestDto.getLongitude(), timestamp);
        locationHistoryRepository.save(locationHistory);

        personRepository.save(person);

        return new LocationResponseDto(person.getLatitude(), person.getLongitude(), timestamp);
    }
}
