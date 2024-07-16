package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.service.IncomeService;
import com.moneytrackerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/income")
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

    @GetMapping
    public List<Income> getIncomes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        AppUser user = userService.findByUsername(username);
        return incomeService.findByUserId(user.getId());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteIncome(@RequestParam Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        AppUser user = userService.findByUsername(username);
        List<Income> userIncomes = incomeService.findByUserId(user.getId());
        boolean incomeExists = userIncomes.stream().anyMatch(income -> income.getId().equals(id));
        if (incomeExists) {
            incomeService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>("You do not own this Income", HttpStatus.UNAUTHORIZED);
        }
    }
}