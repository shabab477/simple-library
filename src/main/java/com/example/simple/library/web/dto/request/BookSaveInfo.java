package com.example.simple.library.web.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BookSaveInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    @NotBlank(message = "{book.error.name.required}")
    private String name;

    @NotBlank(message = "{book.error.isbn.required}")
    private String isbn;

    @NotBlank(message = "{book.error.copyCount.required}")
    private Integer copyCount;

    @NotNull(message = "{book.error.publishedDate.required}")
    private Date publishedDate;

    @NotEmpty(message = "{book.error.authorNames.required}")
    private List<String> authorNames;

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

    public Integer getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }
}
