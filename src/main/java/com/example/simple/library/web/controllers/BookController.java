package com.example.simple.library.web.controllers;

import com.example.simple.library.entities.Book;
import com.example.simple.library.services.BookService;
import com.example.simple.library.web.dto.request.BookSaveInfo;
import com.example.simple.library.web.dto.response.DetailedBookInfo;
import com.example.simple.library.web.dto.response.GenericListResponse;
import com.example.simple.library.web.dto.response.MinimalBookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> showAllBooks() {

        List<Book> books = bookService.getAllBooks();

        return ResponseEntity.ok(new GenericListResponse<>(books, MinimalBookInfo::new));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable int bookId) {

        Book book = bookService.getBook(bookId);

        return ResponseEntity.ok(new DetailedBookInfo(book));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> saveBook(@RequestBody BookSaveInfo saveInfo) {

        Book book = bookService.saveBook(saveInfo);

        return ResponseEntity.ok(new DetailedBookInfo(book));
    }

    @PutMapping("/{bookId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> updateBook(@RequestBody BookSaveInfo saveInfo, @PathVariable int bookId) {

        Book book = bookService.updateBook(saveInfo, bookId);

        return ResponseEntity.ok(new DetailedBookInfo(book));
    }

    @DeleteMapping("/{bookId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteBook(@PathVariable int bookId) {

        Book book = bookService.deleteBook(bookId);

        return ResponseEntity.ok(new DetailedBookInfo(book));
    }

    @PostMapping("/submit/{bookId}")
    public ResponseEntity<?> submitBook(Principal currentUser, @PathVariable int bookId) {

        Book book = bookService.submitBook(currentUser, bookId);

        return ResponseEntity.ok(new DetailedBookInfo(book));
    }

}
