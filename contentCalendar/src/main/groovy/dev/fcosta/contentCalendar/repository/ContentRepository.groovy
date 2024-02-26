package dev.fcosta.contentCalendar.repository

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.model.Status
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ContentRepository extends ListCrudRepository<Content,Integer> {
    //List<Content> findAllByTitleContains(String keyword) // case sensitive
    List<Content> findAllByTitleContainsIgnoreCase(String keyword) // case insensitive

    @Query("""SELECT * FROM content WHERE status = :status""") // @Query: ident
    // ifies a query method
    //@Modifying // identifies a query method which modifies the entity (modifying query)
    List<Content> findAllByStatus(@Param("status") Status status)
}