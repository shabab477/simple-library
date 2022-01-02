package com.example.simple.library.services;

import com.example.simple.library.entities.AppUser;
import com.example.simple.library.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> appUserOptional = userRepository.findAppUserByEmail(email);

        return appUserOptional.map(appUser -> {

            List<SimpleGrantedAuthority> grantedAuthorities = appUser.getRoles()
                    .stream()
                    .map(appUserRole -> new SimpleGrantedAuthority(appUserRole.getName()))
                    .collect(Collectors.toList());

            return new User(appUser.getEmail(), appUser.getPassword(), grantedAuthorities);
        }).orElse(null);
    }
}
