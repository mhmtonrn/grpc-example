package io.monarch.controller;

import io.monarch.service.BookAuthorServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.Descriptors;

@RestController
public class BookAuthorController {

    private final BookAuthorServiceImpl bookAuthorServiceImpl;

    public BookAuthorController(BookAuthorServiceImpl bookAuthorServiceImpl) {
        this.bookAuthorServiceImpl = bookAuthorServiceImpl;
    }

    @GetMapping("/author/{id}")
    public Map<Descriptors.FieldDescriptor, Object> getAuthor(@PathVariable String id) {
        return bookAuthorServiceImpl.getAuthor(Integer.parseInt(id));
    }

    @GetMapping("/book/{authorId}")
    public CompletableFuture<List<Map<Descriptors.FieldDescriptor, Object>>> getBookByAuthor(@PathVariable String authorId) throws InterruptedException {
        return bookAuthorServiceImpl.getBooksByAuthor(Integer.parseInt(authorId));
    }
}