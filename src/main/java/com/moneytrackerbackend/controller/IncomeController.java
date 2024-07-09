package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.service.IncomeService;
import com.moneytrackerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        AppUser user = userService.findByUsername(username);
        income.setUser(user);
        return incomeService.save(income);
    }
}
