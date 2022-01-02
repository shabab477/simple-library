package com.example.simple.library.entities;

import com.example.simple.library.web.dto.request.AppUserSaveInfo;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.simple.library.utils.Constants.BORROWED_LIMIT;

@Entity
public class AppUser implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<AppUserRole> roles;

    @Embedded
    private TemporalProperties temporalProperties;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_users_books",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "id"))
    private Set<Book> borrowedBooks;

    public AppUser(){}

    public AppUser(AppUserSaveInfo saveInfo) {
        this.temporalProperties = new TemporalProperties();
        this.email = saveInfo.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<AppUserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppUserRole> roles) {
        this.roles = roles;
    }

    public TemporalProperties getTemporalProperties() {
        return temporalProperties;
    }

    public void setTemporalProperties(TemporalProperties temporalProperties) {
        this.temporalProperties = temporalProperties;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public boolean hasReachedLimit() {

        return this.getBorrowedBooks().size() >= BORROWED_LIMIT;
    }

    public boolean hasNotReachedLimit() {

        return !this.hasReachedLimit();
    }

    public boolean hasBook(Book book) {

        return this.getBorrowedBooks().contains(book);
    }

    public boolean hasNoBook(Book book) {

        return !this.hasBook(book);
    }

    public void addBorrowedBooks(Book borrowedBook) {

        this.getBorrowedBooks().add(borrowedBook);
        borrowedBook.addIssuedTo(this);
    }

    public void removeBorrowedBook(Book book) {
        this.getBorrowedBooks().remove(book);
        book.removeBorrower(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(email, appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
