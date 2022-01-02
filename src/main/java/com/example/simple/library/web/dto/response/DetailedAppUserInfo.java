package com.example.simple.library.web.dto.response;

import com.example.simple.library.entities.AppUser;
import com.example.simple.library.entities.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailedAppUserInfo extends MinimalAppUserInfo implements Serializable {

    private List<BookInfo> borrowedBooks;

    public DetailedAppUserInfo() {
    }

    public DetailedAppUserInfo(AppUser appUser) {
        super(appUser);

        this.borrowedBooks = appUser.getBorrowedBooks()
                .stream()
                .map(BookInfo::new)
                .collect(Collectors.toList());
    }

    public List<BookInfo> getBorrowedBooks() {
        return borrowedBooks;
    }

    private class BookInfo implements Serializable {
        public static final long serialVersionUID = 1L;

        private int id;
        private String name;
        private List<String> authors;

        public BookInfo(Book book) {
            this.id = book.getId();
            this.name = book.getBookMeta().getName();
            this.authors = new ArrayList<>(book.getBookMeta().getAuthorNames());
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getAuthors() {
            return authors;
        }

        public void setAuthors(List<String> authors) {
            this.authors = authors;
        }
    }
}
