package com.example.simple.library.web.dto.response;

import com.example.simple.library.entities.Book;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class MinimalBookInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String bookName;
    private String isbn;
    private Set<String> authors;

    private int copyCount;
    private Date publishedDate;

    public MinimalBookInfo() {
    }

    public MinimalBookInfo(Book book) {
        this.id = book.getId();
        this.bookName = book.getBookMeta().getName();
        this.isbn = book.getBookMeta().getIsbn();
        this.copyCount = book.getCopyCount();

        this.authors = book.getBookMeta().getAuthorNames();
        this.publishedDate = book.getBookMeta().getPublishedDate();
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }
}
