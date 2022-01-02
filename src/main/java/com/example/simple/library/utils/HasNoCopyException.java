package com.example.simple.library.utils;

import com.example.simple.library.entities.Book;

public class HasNoCopyException extends RuntimeException {

    private final Book book;

    public HasNoCopyException(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String getMessage() {
        return "Book has no copy";
    }
}
