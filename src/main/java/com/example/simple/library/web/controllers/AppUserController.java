package com.example.simple.library.web.controllers;

import com.example.simple.library.entities.AppUser;
import com.example.simple.library.services.AppUserService;
import com.example.simple.library.web.dto.request.AppUserSaveInfo;
import com.example.simple.library.web.dto.response.AppUserList;
import com.example.simple.library.web.dto.response.DetailedAppUserInfo;
import com.example.simple.library.web.dto.response.MinimalAppUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public ResponseEntity<?> showAllUsers() {

        return ResponseEntity.ok(new AppUserList(appUserService.getAllAppUsers()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId) {

        AppUser appUser = appUserService.getAppUser(userId);

        return ResponseEntity.ok(new DetailedAppUserInfo(appUser));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> saveUser(@RequestBody @Valid AppUserSaveInfo saveInfo) {

        AppUser appUser = appUserService.saveAppUser(saveInfo);

        return ResponseEntity.ok(new MinimalAppUserInfo(appUser));
    }

    @PutMapping("/{userId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> updateUser(@RequestBody @Valid AppUserSaveInfo saveInfo, @PathVariable int userId) {

        AppUser appUser = appUserService.updateAppUser(saveInfo, userId);

        return ResponseEntity.ok(new MinimalAppUserInfo(appUser));
    }

    @PostMapping("issue/book/{bookId}")
    public ResponseEntity<?> issueBookToUser(Principal currentUser, @PathVariable int bookId) {

        AppUser appUser = appUserService.issueBookToAppUser(currentUser, bookId);

        return ResponseEntity.ok(new DetailedAppUserInfo(appUser));
    }

    @DeleteMapping("/{userId}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {

        AppUser appUser = appUserService.deleteAppUser(userId);

        return ResponseEntity.ok(new MinimalAppUserInfo(appUser));
    }
}
