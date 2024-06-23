package org.team2project.camealone.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Cacheable(value = "exampleCache", key = "#id")
    public String getExampleData(String id) {
        // 캐싱할 데이터를 가져오는 로직
        return "Data for " + id;
    }
}
