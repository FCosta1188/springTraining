package dev.fcosta.contentCalendar.config

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader implements CommandLineRunner { // CommandLineRunner:  interface used to indicate that a bean should run when it is contained within SpringApplication
    @Override
    void run(String... args) throws Exception {
        println "Hello DataLoader"
    }
}
