package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.AppUser;
import com.moneytrackerbackend.model.Balance;
import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.model.Expense;
import com.moneytrackerbackend.repository.BalanceRepository;
import com.moneytrackerbackend.repository.IncomeRepository;
import com.moneytrackerbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public Balance getBalanceByUserId(Long userId) {
        return balanceRepository.findByUserId(userId);
    }

    public void updateBalance(Long userId) {
        Double totalIncome = incomeRepository.findByUserId(userId).stream()
                .mapToDouble(Income::getAmount).sum();
        Double totalExpense = expenseRepository.findByUserId(userId).stream()
                .mapToDouble(Expense::getAmount).sum();

        Balance balance = balanceRepository.findByUserId(userId);
        if (balance == null) {
            balance = new Balance();
            balance.setUser(new AppUser(userId));
        }
        balance.setTotalIncome(totalIncome);
        balance.setTotalExpense(totalExpense);
        balanceRepository.save(balance);
    }
}
