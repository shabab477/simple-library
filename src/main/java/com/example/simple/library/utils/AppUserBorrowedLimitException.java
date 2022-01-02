package com.example.simple.library.utils;

import com.example.simple.library.entities.AppUser;

public class AppUserBorrowedLimitException extends RuntimeException {

    private final AppUser appUser;

    public AppUserBorrowedLimitException(AppUser appUser) {

        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    @Override
    public String getMessage() {
        return "User has reached limit of borrowal";
    }
}
