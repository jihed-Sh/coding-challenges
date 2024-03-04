package org.example.springsecuirty.auth;

import lombok.RequiredArgsConstructor;
import org.example.springsecuirty.config.JwtService;
import org.example.springsecuirty.user.Role;
import org.example.springsecuirty.user.User;
import org.example.springsecuirty.user.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("auth:" +request.getPassword());

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid credentials");
        }


        var user = repo.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println(request.getPassword());
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getFirstName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        System.out.println(passwordEncoder.encode(request.getPassword()));
        repo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
