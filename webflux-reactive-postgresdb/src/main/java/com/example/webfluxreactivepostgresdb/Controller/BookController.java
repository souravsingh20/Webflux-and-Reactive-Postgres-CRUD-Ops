package com.example.webfluxreactivepostgresdb.Controller;


import com.example.webfluxreactivepostgresdb.Entity.BookEntity;
import com.example.webfluxreactivepostgresdb.Repository.ReactiveBookRepository;
import com.example.webfluxreactivepostgresdb.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@CrossOrigin()
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    ReactiveBookRepository reactiveBookRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    //    CRUD Operations
    @PostMapping("/book")
    public Mono<ResponseEntity<BookEntity>> addBook(@RequestBody Book book) {
        BookEntity bookEntity = new BookEntity(book.getId(), book.getTitle(), book.getAuthor());
        return reactiveBookRepository.save(bookEntity).map(savedBook -> ResponseEntity.ok(savedBook))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/books")
    public Flux<BookEntity> getAllBooks() {
//        return reactiveBookRepository.findAll().delayElements(Duration.ofSeconds(3)).log();
        return reactiveBookRepository.findAll();
    }


    @GetMapping(value = "/live/books", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BookEntity> getAllLiveBooks() {
//        return reactiveBookRepository.findAll().delayElements(Duration.ofSeconds(2)).log();
        return reactiveBookRepository.findAll();
    }

    @GetMapping("/book")
    public Mono<ResponseEntity<BookEntity>> getBookById(@RequestParam(value = "id") Long id) {
        return reactiveBookRepository.findById(id).map(book -> ResponseEntity.ok(book))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/book")
    public Mono<ResponseEntity<BookEntity>> updateBook(@RequestBody Book book) {
        BookEntity bookEntity = new BookEntity(book.getId(), book.getTitle(), book.getAuthor());
        return reactiveBookRepository.save(bookEntity).map(updatedBook -> ResponseEntity.ok(updatedBook))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("book/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") Long id) {
        return reactiveBookRepository.findById(id).flatMap(deletedBook -> reactiveBookRepository.delete(deletedBook))
                .map(deletedBook -> ResponseEntity.ok(deletedBook))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
