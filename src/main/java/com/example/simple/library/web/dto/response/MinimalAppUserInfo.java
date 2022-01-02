package com.example.simple.library.web.dto.response;

import com.example.simple.library.entities.AppUser;

import java.io.Serializable;
import java.util.Date;

public class MinimalAppUserInfo implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public MinimalAppUserInfo() {
    }

    public MinimalAppUserInfo(AppUser appUser) {
        this.id = appUser.getId();
        this.email = appUser.getEmail();

        this.createdAt = appUser.getTemporalProperties().getCreatedAt();
        this.updatedAt = appUser.getTemporalProperties().getUpdatedAt();
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}


