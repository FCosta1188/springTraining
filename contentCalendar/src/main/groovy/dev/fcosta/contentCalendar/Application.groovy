package dev.fcosta.contentCalendar

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.web.client.RestTemplate

// ApplicationContext and Inversion of Control (IoC)
/*
ApplicationContext is a central interface to provide configuration for an application.
This is read-only while the application is running, but may be reloaded if the implementation supports this.

IoC means that Spring manages all the instances of classes in the app (through ApplicationContext);
That's why it's rare to see a "new Object()" statement in Spring.
*/

// Spring Beans (app components)
/*
Instance of a class, which includes configuration metadata (name, scope, etc:).

Classes can be added to the list of beans used by the app (ie, the app components,
included in the ApplicationContext), in two ways:
 - class level: by using the @Component annotation on the target class (see SpringBeanDemo), or a more specific annotation (eg @RestService, @Controller, @Repository, etc.)
 - method level: @Configuration (class) and @Bean (method) (see ConfigDemo)
*/

@SpringBootApplication
class Application {

    static void main(String[] args) {
        //SpringApplication.run(Application.class, args)
        def configAppContext = SpringApplication.run(Application.class, args)

        //list all the beans used by the app
        configAppContext.getBeanDefinitionNames().each {println it}

        //fetch a bean from a @Configuration class (use the @Bean method name in getBean)
        RestTemplate rt = (RestTemplate) configAppContext.getBean("restTemplate")
        println rt
    }

}
