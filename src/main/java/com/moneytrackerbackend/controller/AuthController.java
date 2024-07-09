package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.dto.AuthenticationResponse;
import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.service.UserService;
import com.moneytrackerbackend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser appUser) {
        try {
            if (userService.findByUsername(appUser.getUsername()) != null) {
                return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
            }
            userService.save(appUser);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser appUser) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            UserDetails userDetails = userService.loadUserByUsername(appUser.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUsername(), userService.findByUsername(userDetails.getUsername()).getId()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        }

        if (username != null) {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                AppUser appUser = userService.findByUsername(username);
                Long userId = appUser.getId();
                AuthenticationResponse response = new AuthenticationResponse(jwt, username, userId);
                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}
