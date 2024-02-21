package dev.fcosta.contentCalendar.controller

import dev.fcosta.contentCalendar.config.ContentCalendarProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HomeController {

//  @Values is useful for 1 or few properties to be defined in application.properties,
//  otherwise it is better to use a config file (eg: ContentCalendarProperties) and then use the related fields in application.properties
//  @Value("${cc.welcomeMessage}")
//  String welcomeMessage

    private final ContentCalendarProperties properties

    HomeController(ContentCalendarProperties properties) {
        this.properties = properties
    }

    @GetMapping("/")
    public ContentCalendarProperties home() {
        return properties;
    }

}