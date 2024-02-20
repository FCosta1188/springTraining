package dev.fcosta.contentCalendar.deprecated

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.model.Status
import dev.fcosta.contentCalendar.model.Type
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import java.sql.ResultSet
import java.sql.SQLException
import java.time.LocalDateTime

@Repository
class ContentJdbcTemplateRepository {

    final JdbcTemplate jdbcTemplate

    ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate
    }

    static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated", LocalDateTime.class),
                rs.getString("url")
        )
    }

    List<Content> getAllContent() {
        String sql = "SELECT * FROM Content"
        List<Content> contents = jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow)
        return contents
    }

    Content getContent(int id) {
        String sql = "SELECT * FROM Content WHERE id=?"
        Content content = jdbcTemplate.queryForObject(sql, new Object[]{id}, ContentJdbcTemplateRepository::mapRow)
        return content
    }

    void createContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)"
        jdbcTemplate.update(sql, title, desc, status, contentType, URL)
    }

    void updateContent(int id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?"
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id)
    }

    void deleteContent(int id) {
        String sql = "DELETE FROM Content WHERE id=?"
        jdbcTemplate.update(sql, id)
    }
}
