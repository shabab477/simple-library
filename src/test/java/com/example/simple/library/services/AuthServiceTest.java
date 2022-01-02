package com.example.simple.library.services;

import com.example.simple.library.entities.AppUser;
import com.example.simple.library.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AppUserRepository usersRepo;

    @InjectMocks
    private AuthService authService;

    @Test
    public void shouldReturnNullWhenUserNotFoundFromDB() {

        when(usersRepo.findAppUserByEmail(any())).thenReturn(Optional.empty());
        UserDetails userDetails = authService.loadUserByUsername("any@email.com");

        assertNull(userDetails);
    }

    @Test
    public void shouldReturnUserDetailWhenUserFoundFromDB() {

        AppUser appUser = new AppUser();
        appUser.setEmail("any@gmail.com");
        appUser.setPassword("some");
        appUser.setRoles(new HashSet<>());

        when(usersRepo.findAppUserByEmail(any())).thenReturn(Optional.of(appUser));
        UserDetails userDetails = authService.loadUserByUsername("any@email.com");

        assertNotNull(userDetails);
    }
}
