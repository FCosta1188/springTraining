package dev.fcosta.contentCalendar.controller

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.repository.ContentCollectionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController // accepts http requests and provides responses
@RequestMapping("/api/content") //controller root path
class ContentController {

    final ContentCollectionRepository repository

    // Dependency Injection: https://www.youtube.com/watch?v=TBlB2_4_Sqo
    @Autowired // it is implicit when there is only one constructor
    ContentController(ContentCollectionRepository contentCollectionRepository) { // The dependency ContentCollectionRepository is injected into ContentController via constructor
        //this.repository = new ContentCollectionRepository() // standard approach
        this.repository = contentCollectionRepository // Spring IoC approach
    }

    // findAll GET request
    @GetMapping("") // GET path
    List<Content> findAll() {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    Optional<Content> findById(@PathVariable("id") Integer id) {
        return repository.findById(id)

/*        def contentById = repository.findById(id)

        if (contentById.isEmpty())
            {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found")}
        else
            {return repository.findById(id)}*/

    }
}
