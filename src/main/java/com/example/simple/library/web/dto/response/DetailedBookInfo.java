package com.example.simple.library.web.dto.response;

import com.example.simple.library.entities.Book;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class DetailedBookInfo extends MinimalBookInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    private List<MinimalAppUserInfo> borrowers;

    public DetailedBookInfo() {
    }

    public DetailedBookInfo(Book book) {
        super(book);

        this.borrowers = book.getBorrowedBy()
                .stream()
                .map(MinimalAppUserInfo::new)
                .collect(Collectors.toList());
    }

    public List<MinimalAppUserInfo> getBorrowers() {
        return borrowers;
    }
}
