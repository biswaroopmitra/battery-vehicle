package com.example.BatteryStateOfHealth.Battery.authentication.config;

import com.example.BatteryStateOfHealth.Battery.user.AppUser;
import com.example.BatteryStateOfHealth.Battery.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    public AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findUserByUsername(username);
        return user.map(CustomUserAuthDetails::new).orElseThrow(()->new UsernameNotFoundException("Invalid user"));
    }
}
