package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.dto.BalanceDto;
import com.moneytrackerbackend.model.Balance;
import com.moneytrackerbackend.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{userId}")
    public BalanceDto getBalanceByUserId(@PathVariable Long userId) {
        Balance balance = balanceService.getBalanceByUserId(userId);
        return new BalanceDto(
                userId,
                balance.getTotalIncome(),
                balance.getTotalExpense(),
                balance.getBalance()
        );
    }

    @PostMapping("/{userId}/update")
    public void updateBalance(@PathVariable Long userId) {
        balanceService.updateBalance(userId);
    }
}
