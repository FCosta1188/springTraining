package dev.fcosta.contentCalendar.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration // scan for @Bean definitions (methods, which are considered as beans)
class ConfigDemo {

    @Bean
    RestTemplate restTemplate() { // the return type should be a class, not a primitive type
        new RestTemplateBuilder().build()
    }

}
