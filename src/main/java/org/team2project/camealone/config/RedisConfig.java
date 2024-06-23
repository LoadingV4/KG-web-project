package org.team2project.camealone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(
            "redis-cluster-ro.ybqofh.ng.0001.apn2.cache.amazonaws.com", 6379
        );
        // 필요시 비밀번호 설정
        // standaloneConfig.setPassword("your_redis_password");

        return new LettuceConnectionFactory(standaloneConfig);
    }
}

