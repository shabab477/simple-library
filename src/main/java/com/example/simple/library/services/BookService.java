package com.example.simple.library.services;

import com.example.simple.library.entities.AppUser;
import com.example.simple.library.entities.Book;
import com.example.simple.library.entities.BookMeta;
import com.example.simple.library.entities.TemporalProperties;
import com.example.simple.library.repository.AppUserRepository;
import com.example.simple.library.repository.BookRepository;
import com.example.simple.library.web.dto.request.BookSaveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AppUserRepository userRepository;

    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    public Book getBook(int bookId) {

        return bookRepository.findById(bookId).orElseThrow();
    }

    @Transactional
    public Book saveBook(BookSaveInfo saveInfo) {

        Book book = new Book(saveInfo);
        book.setBookMeta(new BookMeta(book, saveInfo));
        book.setBorrowedBy(new HashSet<>());

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(BookSaveInfo saveInfo, int bookId) {

        Book book = bookRepository.findBookByIdForUpdate(bookId).orElseThrow();

        TemporalProperties temporalProperties = book.getTemporalProperties();
        temporalProperties.setUpdatedAt(new Date());

        book.setTemporalProperties(temporalProperties);

        book.getBookMeta().getAuthorNames().addAll(saveInfo.getAuthorNames());
        book.getBookMeta().setIsbn(saveInfo.getIsbn());
        book.getBookMeta().setPublishedDate(saveInfo.getPublishedDate());

        return bookRepository.save(book);
    }

    @Transactional
    public Book submitBook(Principal principal, int bookId) {

        User loggedInUser = (User)(((UsernamePasswordAuthenticationToken) principal).getPrincipal());

        AppUser loggedInAppUser = userRepository.findAppUserByEmail(loggedInUser.getUsername()).orElseThrow();

        AppUser appUser = userRepository.findAppUserByIdForUpdate(loggedInAppUser.getId()).orElseThrow();
        Book book = bookRepository.findBookByIdForUpdate(bookId).orElseThrow();

        handleBusinessCases(appUser, book);

        appUser.removeBorrowedBook(book);
        userRepository.saveAndFlush(appUser);

        return bookRepository.saveAndFlush(book);
    }

    private void handleBusinessCases(AppUser appUser, Book book) {

        if (appUser.hasNoBook(book)) {

            throw new NoSuchElementException();
        }
    }

    @Transactional
    public Book deleteBook(int bookId) {

        Book book = bookRepository.findBookByIdForUpdate(bookId).orElseThrow();
        bookRepository.delete(book);

        return book;
    }
}
