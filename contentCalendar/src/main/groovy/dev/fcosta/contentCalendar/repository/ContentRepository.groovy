package dev.fcosta.contentCalendar.repository

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.model.Status
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.query.Param

interface ContentRepository extends ListCrudRepository<Content, Integer> {
    List<Content> findAllByTitleContains(String keyword)

    @Query("""SELECT * FROM content WHERE status = :status""")
    List<Content> findAllByStatus(@Param("status") Status status)
}