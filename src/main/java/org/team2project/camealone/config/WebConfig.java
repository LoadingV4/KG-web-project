package org.team2project.camealone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")  // 클라이언트 URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/js/**")
                        .addResourceLocations("classpath:/static/js/")
                        .setCachePeriod(3600) // 캐시 기간 설정 (초 단위)
                        .resourceChain(true);
                registry.addResourceHandler("/css/**")
                        .addResourceLocations("classpath:/static/css/")
                        .setCachePeriod(3600)
                        .resourceChain(true);
                registry.addResourceHandler("/images/**")
                        .addResourceLocations("classpath:/static/images/")
                        .setCachePeriod(3600)
                        .resourceChain(true);
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/")
                        .setCachePeriod(3600)
                        .resourceChain(true);
            }
        };
    }
}
