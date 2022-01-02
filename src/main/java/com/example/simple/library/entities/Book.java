package com.example.simple.library.entities;

import com.example.simple.library.web.dto.request.BookSaveInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Book implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int copyCount;

    @ManyToMany(mappedBy = "borrowedBooks", fetch = FetchType.EAGER)
    private Collection<AppUser> borrowedBy;

    @Embedded
    private TemporalProperties temporalProperties;

    @OneToOne(
        mappedBy = "book",
        orphanRemoval = true,
        cascade = CascadeType.ALL
    )
    private BookMeta bookMeta;

    public Book() {
    }

    public Book(BookSaveInfo saveInfo) {
        this.copyCount = saveInfo.getCopyCount();
        this.temporalProperties = new TemporalProperties();
    }

    public Book(BookSaveInfo saveInfo, int id) {
        this(saveInfo);

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(int copyCount) {
        this.copyCount = copyCount;
    }

    public Collection<AppUser> getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Collection<AppUser> borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public TemporalProperties getTemporalProperties() {
        return temporalProperties;
    }

    public void setTemporalProperties(TemporalProperties temporalProperties) {
        this.temporalProperties = temporalProperties;
    }

    public BookMeta getBookMeta() {
        return bookMeta;
    }

    public void setBookMeta(BookMeta bookMeta) {
        this.bookMeta = bookMeta;
    }

    public boolean hasMoreCopies() {

        return this.getCopyCount() > 0;
    }

    public boolean hasNoCopy() {

        return !this.hasMoreCopies();
    }

    public void addIssuedTo(AppUser appUser) {

        if (!this.getBorrowedBy().contains(appUser)) {
            this.copyCount = this.copyCount - 1;
        }

        this.getBorrowedBy().add(appUser);

    }

    public void removeBorrower(AppUser appUser) {
        this.getBorrowedBy().remove(appUser);
        this.copyCount = this.copyCount + 1;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookMeta.getName() +
                String.join(",", bookMeta.getAuthorNames())
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Book other = (Book) obj;
        return Objects.equals(bookMeta.getName(), other.getBookMeta().getName()) &&
                Objects.equals(String.join(",", bookMeta.getAuthorNames()),
                        String.join(",", other.getBookMeta().getAuthorNames())
                );
    }
}
