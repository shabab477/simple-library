package com.example.simple.library.repository;

import com.example.simple.library.entities.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Integer> {

    Optional<AppUserRole> findAppUserRoleByName(String roleName);
}
