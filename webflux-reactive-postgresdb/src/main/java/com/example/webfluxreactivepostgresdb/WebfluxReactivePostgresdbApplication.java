package com.example.webfluxreactivepostgresdb;

import com.example.webfluxreactivepostgresdb.Repository.ReactiveBookRepository;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@EnableCaching
public class WebfluxReactivePostgresdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxReactivePostgresdbApplication.class, args);
    }

    @Bean
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }

}
