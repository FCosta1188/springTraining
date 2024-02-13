package dev.fcosta.contentCalendar.model

import java.time.LocalDateTime

// record classes (since JDK16)
// https://groovy.apache.org/blog/groovy-records
/*
A compact form for declaring "data" classes (POJO, Java Bean; similar to @Canonical in Groovy).
Such classes hold "data" and (almost) nothing else.
Java chose the very common scenario of holding immutable data. With this context, and following a
few restrictions, it becomes a relatively easy task for the Java compiler to generate much of the
boilerplate for such classes (constructors, getters/setters, toString, etc.).
*/

record Content(
        Integer id, // getter: id(), setter: setProperty(String propertyName, Object newValue)
        String title,
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {

}
