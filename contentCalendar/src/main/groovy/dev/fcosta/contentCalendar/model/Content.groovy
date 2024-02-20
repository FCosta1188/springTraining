package dev.fcosta.contentCalendar.model

import groovy.transform.Canonical
import java.time.LocalDateTime

// Data validation and constraints dependency: spring-boot-starter-validation
// (to be coupled with @Valid in ContentController)
import jakarta.validation.constraints.NotEmpty

// Entity mapping
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

@Canonical
class Content {
    @Id Integer id;
    @NotEmpty String title
    @Column("description") String desc
    Status status
    Type contentType
    LocalDateTime dateCreated
    LocalDateTime dateUpdated
    String url
}


// !!! record seems not to work with @Id and @Column annotations in Groovy !!!
/*// record classes (since JDK16)
// https://groovy.apache.org/blog/groovy-records
*//*
A compact form for declaring "data" classes (POJO, Java Bean; similar to @Canonical in Groovy).
Such classes hold "data" and (almost) nothing else.
Java chose the very common scenario of holding immutable data. With this context, and following a
few restrictions, it becomes a relatively easy task for the Java compiler to generate much of the
boilerplate for such classes (constructors, getters/setters, toString, etc.).
*/

/*
record Content(
        @Id
        private final Integer id,
        String title,
        //@Column("description")
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {
    @PersistenceCreator
    Content(Integer id) {
        this.id = id
    }
}
*/
