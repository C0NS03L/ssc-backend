package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.dto.BalanceDto;
import com.moneytrackerbackend.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{userId}")
    public BalanceDto getBalance(@PathVariable Long userId) {
        return balanceService.getBalance(userId);
    }
}
