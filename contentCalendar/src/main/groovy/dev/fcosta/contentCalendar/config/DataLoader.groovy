package dev.fcosta.contentCalendar.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.repository.ContentRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader implements CommandLineRunner { // CommandLineRunner:  interface used to indicate that a bean should run when it is contained within SpringApplication

    final ContentRepository repository
    final ObjectMapper objectMapper

    DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository
        this.objectMapper = objectMapper
    }

    @Override
    void run(String... args) throws Exception {
        /*println "----------------"
        println "Hello DataLoader"
        println "----------------"*/

        // init the repo with the contents of ./resources/data/content.json
        //if(!repository.count()) { // init with these dummy data only if the db is empty (repository.count() == 0)
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
                repository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}))
            }
        //}
    }
}
