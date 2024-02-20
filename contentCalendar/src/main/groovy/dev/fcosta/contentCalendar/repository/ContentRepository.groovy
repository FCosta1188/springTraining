package dev.fcosta.contentCalendar.repository

import dev.fcosta.contentCalendar.model.Content
import org.springframework.data.repository.ListCrudRepository

interface ContentRepository extends ListCrudRepository<Content, Integer> {

}