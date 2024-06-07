package com.blogapp.controller;

import com.blogapp.entity.User;
import com.blogapp.payload.LoginDto;
import com.blogapp.payload.Signup;
import com.blogapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // http://localhost:8080/api/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Signup signup){

        if (userRepo.existsByEmail(signup.getEmail())){
            return new ResponseEntity<>("email Id is already registered", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (userRepo.existsByUsername(signup.getUsername())){
            return new ResponseEntity<>("Username is existed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = new User();
        user.setName(signup.getName());
        user.setEmail(signup.getEmail());
        user.setUsername(signup.getUsername());
        user.setPassword(passwordEncoder.encode(signup.getPassword()));

        userRepo.save(user);
        return new ResponseEntity<>("User is registered", HttpStatus.CREATED);

    }
    @PostMapping("/signIn")
    public ResponseEntity <String> signIn(@RequestBody LoginDto loginDto){

        //1. Supply loginDto.getUsername()-username to loadByUser method in CustomUserDetail class.
        //2.It will compare expected credentials with actual credentials given by loadByUser method

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //similar to session variable
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<>("signIn is successful", HttpStatus.OK);
    }
}
