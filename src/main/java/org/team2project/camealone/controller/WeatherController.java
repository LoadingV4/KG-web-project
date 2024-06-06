package org.team2project.camealone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {
    // 프론트엔드에서 api키를 쓰는 것은 보안상 문제가 되므로 키를 백엔드에서 받아오도록 해야한다
    // 이 컨트롤러는 api 엔드포인트를 설정하여 getWeather함수가 api키를 요청하면 키를 주는 형식이다.

    @Value("${weather.api.key}") // application.properties에 정의되어있음
    private String apiKey;

    @GetMapping("/api/weather")
    public ResponseEntity<String> getWeather(@RequestParam double lat, @RequestParam double lon) {
        // api키 엔드포인트
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric&lang=kr";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(result);
    }
}

