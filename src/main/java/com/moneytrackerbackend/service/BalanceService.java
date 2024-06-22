package com.moneytrackerbackend.service;

import com.moneytrackerbackend.dto.BalanceDto;
import com.moneytrackerbackend.repository.ExpenseRepository;
import com.moneytrackerbackend.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public BalanceDto getBalance(Long userId) {
        BigDecimal totalIncome = incomeRepository.findTotalIncomeByUserId(userId).orElse(BigDecimal.ZERO);
        BigDecimal totalExpense = expenseRepository.findTotalExpenseByUserId(userId).orElse(BigDecimal.ZERO);
        BigDecimal balance = totalIncome.subtract(totalExpense);
        return new BalanceDto(totalIncome, totalExpense, balance);
    }
}
