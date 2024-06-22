package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.Balance;
import com.moneytrackerbackend.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public Balance findByUserId(Long userId) {
        return balanceRepository.findByUserId(userId).orElse(null);
    }

    public Balance updateBalance(Long userId, BigDecimal income, BigDecimal expense) {
        Balance balance = balanceRepository.findByUserId(userId).orElse(new Balance());
        balance.setTotalIncome(balance.getTotalIncome().add(income));
        balance.setTotalExpense(balance.getTotalExpense().add(expense));
        balance.setNetBalance(balance.getTotalIncome().subtract(balance.getTotalExpense()));
        balance.setUserId(userId);
        return balanceRepository.save(balance);
    }
}
