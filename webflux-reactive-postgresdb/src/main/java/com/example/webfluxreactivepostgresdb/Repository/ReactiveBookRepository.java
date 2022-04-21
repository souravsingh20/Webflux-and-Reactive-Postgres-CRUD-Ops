package com.example.webfluxreactivepostgresdb.Repository;


import com.example.webfluxreactivepostgresdb.Entity.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveBookRepository extends ReactiveCrudRepository<BookEntity, Long> {

}
