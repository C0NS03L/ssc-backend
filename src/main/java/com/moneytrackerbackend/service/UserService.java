package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService, CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    public AppUser save(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public AppUser findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = "admin";
        String adminPassword = "1234";

        if (findByUsername(adminUsername) == null) {
            AppUser admin = new AppUser();
            admin.setUsername(adminUsername);
            admin.setPassword(adminPassword);
            save(admin);
            System.out.println("Admin user created with username: " + adminUsername + " and password: " + adminPassword);
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}
