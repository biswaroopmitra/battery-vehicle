package com.example.BatteryStateOfHealth.Battery.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);

    Optional<AppUser> findUserByUsername(String username);
}
