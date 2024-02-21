package dev.fcosta.contentCalendar.config

import groovy.transform.Canonical
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

// value: prefix to be used for accessing the properties. It requires the Annotation Processor dependency (see pom).
// a related json file will be generated after build/run: contentCalendar/target/classes/META-INF/spring-configuration-metadata.json
@ConfigurationProperties(value = "cc")

@Component
@Primary
@Canonical
class ContentCalendarProperties {
    String welcomeMessage
    String about
}
