package com.wootechco.Makey.gps.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy")
public class ProxyController {

    @Value("${my.secret-key}")  // 카카오 API 키를 환경변수에서 가져옵니다.
    private String kakaoApiKey;

    private final RestTemplate restTemplate;

    public ProxyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 카카오 맵 API를 호출하는 예시 (지도 관련 API 호출)
    @GetMapping("/kakao-map")
    public ResponseEntity<String> getKakaoMapData() {
        String url = "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + kakaoApiKey;

        // 카카오 API 호출
        String response = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok(response);
    }
}
