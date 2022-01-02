package com.example.simple.library.services;

import com.example.simple.library.entities.AppUser;
import com.example.simple.library.entities.AppUserRole;
import com.example.simple.library.entities.Book;
import com.example.simple.library.repository.AppUserRepository;
import com.example.simple.library.repository.AppUserRoleRepository;
import com.example.simple.library.repository.BookRepository;
import com.example.simple.library.utils.AppUserBorrowedLimitException;
import com.example.simple.library.utils.HasNoCopyException;
import com.example.simple.library.web.dto.request.AppUserSaveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.example.simple.library.utils.Constants.KAFKA_EMAIL_SEND_TOPIC;
import static com.example.simple.library.utils.Constants.ROLE_USER;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private AppUserRoleRepository userRoleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public List<AppUser> getAllAppUsers() {

        return userRepository.findAll();
    }

    public AppUser getAppUser(int userId) {

        return userRepository.findById(userId).orElseThrow();
    }

    @Transactional
    public AppUser saveAppUser(AppUserSaveInfo saveInfo) {

        AppUser appUser = new AppUser(saveInfo);

        return populateAppUserInfo(appUser, saveInfo);
    }

    private AppUser populateAppUserInfo(AppUser appUser, AppUserSaveInfo saveInfo) {

        String hashedPassword = bcryptPasswordEncoder.encode(saveInfo.getPassword());
        appUser.setPassword(hashedPassword);

        AppUserRole appUserRole = userRoleRepository.findAppUserRoleByName(ROLE_USER).orElseThrow();
        appUser.setRoles(new HashSet<>(List.of(appUserRole)));

        userRepository.save(appUser);

        return appUser;
    }

    @Transactional
    public AppUser updateAppUser(AppUserSaveInfo saveInfo, int userId) {

        AppUser appUser = userRepository.findAppUserByIdForUpdate(userId).orElseThrow();
        appUser.getTemporalProperties().setUpdatedAt(new Date());
        appUser.setEmail(saveInfo.getEmail());

        return populateAppUserInfo(appUser, saveInfo);
    }

    @Transactional
    public AppUser issueBookToAppUser(Principal principal, int bookId) {

        User loggedInUser = (User) (((UsernamePasswordAuthenticationToken) principal).getPrincipal());

        int loggedInUserId = userRepository.findAppUserByEmail(loggedInUser.getUsername()).orElseThrow().getId();

        AppUser appUser = userRepository.findAppUserByIdForUpdate(loggedInUserId).orElseThrow();
        Book book = bookRepository.findBookByIdForUpdate(bookId).orElseThrow();

        handleBusinessCases(appUser, book);

        appUser.addBorrowedBooks(book);

        kafkaTemplate.send(KAFKA_EMAIL_SEND_TOPIC, appUser.getEmail());

        return userRepository.saveAndFlush(appUser);
    }

    private void handleBusinessCases(AppUser appUser, Book book) {

        if (appUser.hasReachedLimit()) {

            throw new AppUserBorrowedLimitException(appUser);
        }

        if (book.hasNoCopy()) {

            throw new HasNoCopyException(book);
        }
    }

    @Transactional
    public AppUser deleteAppUser(int userId) {

        AppUser appUser = userRepository.findById(userId).orElseThrow();
        userRepository.delete(appUser);

        return appUser;
    }
}
