package com.wootechco.Makey.elderly.service;

import com.wootechco.Makey.elderly.domain.LocationHistory;
import com.wootechco.Makey.elderly.domain.Person;
import com.wootechco.Makey.elderly.dto.LocationRequestDto;
import com.wootechco.Makey.elderly.dto.LocationResponseDto;
import com.wootechco.Makey.elderly.repository.LocationHistoryRepository;
import com.wootechco.Makey.elderly.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final PersonRepository personRepository;
    private final LocationHistoryRepository locationRepository;

    public LocationResponseDto addLocation(Long personId, LocationRequestDto dto) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("해당 인물이 존재하지 않습니다."));

        LocationHistory location = LocationHistory.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .timestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now())
                .person(person)
                .build();

        return LocationResponseDto.fromEntity(locationRepository.save(location));
    }

    public List<LocationResponseDto> getLocations(Long personId) {
        return locationRepository.findByPersonId(personId)
                .stream()
                .map(LocationResponseDto::fromEntity)
                .toList();
    }
}
