package com.example.simple.library.repository;

import com.example.simple.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static javax.persistence.LockModeType.PESSIMISTIC_READ;
import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    @Lock(PESSIMISTIC_WRITE)
    <S extends Book> S save(S entity);

    @Query("SELECT b from Book b WHERE b.id = :bookId")
    @Lock(PESSIMISTIC_READ)
    Optional<Book> findBookByIdForUpdate(@Param("bookId") int bookId);
}