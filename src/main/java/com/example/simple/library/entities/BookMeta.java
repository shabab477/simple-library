package com.example.simple.library.entities;

import com.example.simple.library.web.dto.request.BookSaveInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class BookMeta implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String isbn;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "authorName")
    private Set<String> authorNames;

    private Date publishedDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookMeta() {}

    public BookMeta(Book book, BookSaveInfo saveInfo) {
        this.book = book;
        this.name = saveInfo.getName();
        this.isbn = saveInfo.getIsbn();

        this.authorNames = saveInfo.getAuthorNames().stream().collect(Collectors.toSet());
        this.publishedDate = saveInfo.getPublishedDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(Set<String> authorNames) {
        this.authorNames = authorNames;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Book getBook() {
        return book;
    }
}
