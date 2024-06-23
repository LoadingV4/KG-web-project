package org.team2project.camealone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.Arrays;

@Configuration
@EnableRedisHttpSession
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 클러스터 구성 설정
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(
            Arrays.asList("redis-cluster-ro.ybqofh.ng.0001.apn2.cache.amazonaws.com:6379")
        );

        // 필요에 따라 비밀번호 설정 (비밀번호가 없다면 주석 처리)
        // clusterConfiguration.setPassword("your_redis_password");

        return new LettuceConnectionFactory(clusterConfiguration);
    }
}
