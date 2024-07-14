package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.Balance;
import com.moneytrackerbackend.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    public Balance findByUserId(Long userId) {
        return balanceRepository.findByUserId(userId).orElse(null);
    }

    public Balance updateBalance(Long userId, BigDecimal income, BigDecimal expense) {
        Optional<Balance> balanceOpt = balanceRepository.findByUserId(userId);
        Balance balance;
        if (balanceOpt.isPresent()) {
            balance = balanceOpt.get();
        } else {
            balance = new Balance();
            balance.setUserId(userId);
        }

        balance.setTotalIncome(balance.getTotalIncome().add(income));
        balance.setTotalExpense(balance.getTotalExpense().add(expense));
        balance.setNetBalance(balance.getTotalIncome().subtract(balance.getTotalExpense()));
        return balanceRepository.save(balance);
    }
}
