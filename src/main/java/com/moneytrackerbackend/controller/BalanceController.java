package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.model.Balance;
import com.moneytrackerbackend.service.BalanceService;
import com.moneytrackerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public Balance getBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        AppUser user = userService.findByUsername(username);
        return balanceService.findByUserId(user.getId());
    }
}
