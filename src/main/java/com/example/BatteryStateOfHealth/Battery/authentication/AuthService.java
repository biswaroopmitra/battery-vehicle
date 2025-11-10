package com.example.BatteryStateOfHealth.Battery.authentication;


import com.example.BatteryStateOfHealth.Battery.user.AppUser;
import com.example.BatteryStateOfHealth.Battery.user.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("auth")
public class AuthService {

    private final AppUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private AuthService(AppUserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager){
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
            loginResponse.user = userRepository.findByUsername(loginRequest.username) ;
            loginResponse.jwt = generateJwt(loginResponse.user);
            return loginResponse;
        } else {
            throw new BadCredentialsException("Invalid user.");
        }
    }

    public String generateJwt(AppUser user){
        return jwtService.createJwt(user.getUsername());
    }
}
