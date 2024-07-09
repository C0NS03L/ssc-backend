package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<AppUser> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        AppUser user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        AppUser existingUser = userService.findByUsername(username);
        user.setId(existingUser.getId());
        return ResponseEntity.ok(userService.save(user));
    }
}
