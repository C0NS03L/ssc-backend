package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.Expense;
import com.moneytrackerbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BalanceService balanceService;

    @Transactional
    public Expense save(Expense expense) {
        Expense savedExpense = expenseRepository.save(expense);
        balanceService.updateBalance(BigDecimal.ZERO, expense.getAmount());
        return savedExpense;
    }

    public List<Expense> findByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    public List<Expense> findByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }
}
