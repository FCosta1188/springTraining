package dev.fcosta.contentCalendar.repository

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.model.Status
import dev.fcosta.contentCalendar.model.Type
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Repository

import java.time.LocalDateTime

@Repository // keep a collection of Content objects in memory, when not working with a DB
class ContentCollectionRepository {

    final List<Content> content = []

    ContentCollectionRepository() {
    }

    @PostConstruct
    /* @PostConstruct is used on a method that
       needs to be executed after dependency injection is done,
       to perform any initialization. */
    void init() {
        Content c1 = new Content(
                1,
                "My First Blog Post",
                "My First Blog Post description",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        )

        Content c2 = new Content(
                2,
                "My First Blog Post",
                "My First Blog Post description",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        )

        content.add(c1)
        content.add(c2)
    }

    List<Content> findAll() {
        return content
    }

    // Optional:
    /* A container object which may or may not contain a non-null value.
     If a value is present, {@code isPresent()} returns {@code true}. If no
     value is present, the object is considered empty and
     {@code isPresent()} returns {@code false}. */
    Optional<Content> findById(Integer id) {
        return content.stream().filter {c -> c.id().equals(id)}.findFirst()
    }
}
