package com.wootechco.Makey.gps.service;

import com.wootechco.Makey.elderly.domain.LocationHistory;
import com.wootechco.Makey.elderly.domain.Person;
import com.wootechco.Makey.elderly.repository.LocationHistoryRepository;
import com.wootechco.Makey.elderly.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GPSService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final PersonRepository personRepository;
    private final LocationHistoryRepository locationHistoryRepository;

    // 외부 API에서 GPS 데이터 받아오기
    public void updatePersonLocation(Long personId, String deviceId) {
        String url = "https://api.gpsprovider.com/device/" + deviceId + "/location";

        try {
            GPSResponse gpsResponse = restTemplate.getForObject(url, GPSResponse.class);

            if (gpsResponse != null) {
                Optional<Person> optionalPerson = personRepository.findById(personId);
                if (optionalPerson.isPresent()) {
                    Person person = optionalPerson.get();

                    // 새로운 위치 이력 생성
                    LocationHistory history = LocationHistory.builder()
                            .person(person)
                            .latitude(gpsResponse.getLatitude())
                            .longitude(gpsResponse.getLongitude())
                            .timestamp(gpsResponse.getTimestamp())
                            .build();

                    locationHistoryRepository.save(history);

                    // Person 현재 위치 갱신
                    person.setLatitude(gpsResponse.getLatitude());
                    person.setLongitude(gpsResponse.getLongitude());
                    person.setLastUpdated(LocalDateTime.now());
                    personRepository.save(person);
                }
            }

        } catch (Exception e) {
            System.err.println("GPS API 호출 실패: " + e.getMessage());
        }
    }

    // GPS API 응답을 담을 내부 DTO
    @lombok.Data
    public static class GPSResponse {
        private Double latitude;
        private Double longitude;
        private LocalDateTime timestamp;
    }
}
