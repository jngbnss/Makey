package com.wootechco.Makey.elderly.service;

import com.wootechco.Makey.elderly.domain.Person;
import com.wootechco.Makey.elderly.dto.PersonRequestDto;
import com.wootechco.Makey.elderly.dto.PersonResponseDto;
import com.wootechco.Makey.elderly.repository.PersonRepository;
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
}
