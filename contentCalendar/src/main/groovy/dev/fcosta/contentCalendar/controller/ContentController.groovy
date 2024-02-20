package dev.fcosta.contentCalendar.controller

import dev.fcosta.contentCalendar.model.Content
import dev.fcosta.contentCalendar.model.Status
import dev.fcosta.contentCalendar.repository.ContentRepository
import jakarta.validation.Valid

// Data validation and constraints dependency: spring-boot-starter-validation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
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
//@Service
class ContentController {

    //final ContentCollectionRepository repository //sample embedded repo
    //final ContentJdbcTemplateRepository repository //db repo
    @Autowired
    final ContentRepository repository // ListCrudRepository repo (Spring Data JDBC)

    // Dependency Injection: https://www.youtube.com/watch?v=TBlB2_4_Sqo
/*  @Autowired // it is implicit when there is only one constructor
    ContentController(ContentCollectionRepository repository) { // The dependency ContentCollectionRepository is injected into ContentController via constructor
        //this.repository = new ContentCollectionRepository() // standard Java approach
        this.repository = repository // Spring IoC approach
    }
*/
    ContentController(ContentRepository repository) {
        this.repository = repository
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

    @GetMapping("/filterTitle/{keyword}")
    List<Content> findByTitle(@PathVariable("keyword") String keyword) {
        repository.findAllByTitleContains(keyword)
    }

    @GetMapping("/filterStatus/{status}")
    List<Content> findByStatus(@PathVariable("status") Status status) {
        repository.findAllByStatus(status)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Content content) { // @Valid: forces the request to apply data validation based on constraints defined in the Content class
        repository.save(content)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Content content, @PathVariable("id") Integer id) {
        if (repository.findById(id).isPresent())
            //repository.updateById(content) // ContentCollectionRepository
            repository.save(content) // ContentRepository
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
