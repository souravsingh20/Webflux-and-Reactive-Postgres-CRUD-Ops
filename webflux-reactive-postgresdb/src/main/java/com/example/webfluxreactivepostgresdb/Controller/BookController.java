package com.example.webfluxreactivepostgresdb.Controller;


import com.example.webfluxreactivepostgresdb.Entity.BookEntity;
import com.example.webfluxreactivepostgresdb.Repository.ReactiveBookRepository;
import com.example.webfluxreactivepostgresdb.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @PostMapping("/add-book")
    public Mono<BookEntity> addBook(@RequestBody Book book)
    {
        BookEntity bookEntity = new BookEntity(book.getId(),book.getTitle(),book.getAuthor());
        return reactiveBookRepository.save(bookEntity);
    }

    @GetMapping("/get-all-books")
    public Flux<BookEntity> getAllBooks()
    {
//        return reactiveBookRepository.findAll().delayElements(Duration.ofSeconds(3)).log();
        return reactiveBookRepository.findAll();
    }

    @GetMapping(value = "/get-all-books-live", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BookEntity> getAllLiveBooks()
    {
//        return reactiveBookRepository.findAll().delayElements(Duration.ofSeconds(2)).log();
        return reactiveBookRepository.findAll();
    }

    @GetMapping("/get-book-by-id")
    public Mono<BookEntity> getBookById(@RequestParam (value = "id") Long id)
    {
        return reactiveBookRepository.findById(id);
    }

    @PutMapping("/update-book")
    public Mono<BookEntity> updateBook(@RequestBody Book book){
        BookEntity bookEntity = new BookEntity(book.getId(),book.getTitle(),book.getAuthor());
        return reactiveBookRepository.save(bookEntity);
    }

    @DeleteMapping("delete-book/{id}")
    public Mono<BookEntity> deleteBook(@PathVariable("id") Long id){
        return reactiveBookRepository.findById(id)
                .doOnSuccess(bookEntity -> reactiveBookRepository.delete(bookEntity).subscribe());
    }
}
