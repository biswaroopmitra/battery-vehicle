package com.example.BatteryStateOfHealth.Battery.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    Optional<User> findUserByUserName(String userName);
}
