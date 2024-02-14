package dev.fcosta.contentCalendar.controller

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.repository.ContentCollectionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController // accepts http requests and provides responses
@RequestMapping("/api/content") //controller root path
@CrossOrigin // CORS = Cross-Origin Resource Sharing. If the annotation is used without options, CORS block is disabled for all origins. // Sample CORS error: "Access to fetch at 'http://localhost:8080/api/content' from origin 'http://127.0.0.1:5500' has been blocked by CORS policy" (server/backend and client/frontend addresses are different, that's why it's cross-origin).
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
        def contentById = repository.findById(id)

        // Handling null
        if (contentById.isPresent())
            return contentById
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found")
        // --- OR ---
        // return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found")) // change return type from Optional<Content> to Content
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Content content) {
        repository.save(content)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Content content, @PathVariable("id") Integer id) {
        if (repository.findById(id).isPresent())
            repository.updateById(content)
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found")
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Integer id) {
        if (repository.findById(id).isPresent())
            repository.deleteById(id)
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found")
    }
}
