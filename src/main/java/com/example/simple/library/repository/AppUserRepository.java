package com.example.simple.library.repository;

import com.example.simple.library.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static javax.persistence.LockModeType.PESSIMISTIC_READ;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByEmail(String email);

    @Query("SELECT au from AppUser au WHERE au.id = :userId")
    @Lock(PESSIMISTIC_READ)
    Optional<AppUser> findAppUserByIdForUpdate(@Param("userId") int userId);
}
