package com.jwt.security.controller;

import com.jwt.security.auth.AuthenticationService;
import com.jwt.security.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RestTempleteHelperController {
    @GetMapping("/get/{email}")
    public ResponseEntity<?> get(@PathVariable("email") String email){
        AuthenticationService authenticationService;

        return null;
    }
}
