package dev.fcosta.contentCalendar.deprecated

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.model.Status
import dev.fcosta.contentCalendar.model.Type
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Repository

import java.time.LocalDateTime

@Repository // keep a collection of Content objects in memory, when not working with a DB
class ContentCollectionRepository {

    final List<Content> contentList = []

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
                "My Second Blog Post",
                "My Second Blog Post description",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        )

        contentList.add(c1)
        contentList.add(c2)
    }

    List<Content> findAll() {
        return contentList
    }

    // Optional:
    /* A container object which may or may not contain a non-null value.
     If a value is present, {@code isPresent()} returns {@code true}. If no
     value is present, the object is considered empty and
     {@code isPresent()} returns {@code false}. */
    Optional<Content> findById(Integer id) {
        return contentList.stream().filter { c -> c.id().equals(id)}.findFirst()
    }

    void save(Content content) {
        contentList.add(content)
    }

    void updateById(Content content) {
        deleteById(content.id())
        contentList.add(content)
    }

    void deleteById(Integer id) {
        contentList.removeIf {c -> c.id().equals(id)}
    }
}
