package com.example.BatteryStateOfHealth.Battery.authentication;


import com.example.BatteryStateOfHealth.Battery.user.User;
import com.example.BatteryStateOfHealth.Battery.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@Service("auth")
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private AuthService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public LoginDto userLogin( LoginRequestDto loginRequest){
        Authentication authenticate = authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                loginRequest.getUsername(),
                                                loginRequest.getPassword())
                                        );

        if(authenticate.isAuthenticated()) {
            LoginDto loginResponse = new LoginDto();
            loginResponse.loginSuccess = true;
            loginResponse.user = userRepository.findByUserName(loginRequest.username) ;
            loginResponse.jwt = generateJwt(loginResponse.user);
            return loginResponse;
        } else {
            throw new BadCredentialsException("Invalid user.");
        }
    }

    public String generateJwt(User user){
        return jwtService.createJwt(user.getUsername());
    }
}
