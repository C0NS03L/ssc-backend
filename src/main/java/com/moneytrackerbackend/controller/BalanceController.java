package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.Balance;
import com.moneytrackerbackend.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{userId}")
    public Balance getBalance(@PathVariable Long userId) {
        return balanceService.findByUserId(userId);
    }
}
