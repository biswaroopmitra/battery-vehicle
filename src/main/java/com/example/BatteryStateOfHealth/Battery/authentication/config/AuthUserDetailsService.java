package com.example.BatteryStateOfHealth.Battery.authentication.config;

import com.example.BatteryStateOfHealth.Battery.user.User;
import com.example.BatteryStateOfHealth.Battery.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserName(username);
        return user.map(CustomUserAuthDetails::new).orElseThrow(()->new UsernameNotFoundException("Invalid user"));
    }
}
