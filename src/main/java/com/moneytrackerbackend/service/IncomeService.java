package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private BalanceService balanceService;

    @Transactional
    public Income save(Income income) {
        Income savedIncome = incomeRepository.save(income);
        balanceService.updateBalance(income.getUser().getId(), income.getAmount(), BigDecimal.ZERO);
        return savedIncome;
    }

    public List<Income> findByCategory(String category) {
        return incomeRepository.findByCategory(category);
    }

    public List<Income> findByUserId(Long userId) {
        return incomeRepository.findByUserId(userId);
    }

    public void deleteById(Long id) {
        Income income = incomeRepository.findById(id).orElse(null);
        if (income != null) {
            incomeRepository.deleteById(id);
            balanceService.updateBalance(income.getUser().getId(), BigDecimal.ZERO, income.getAmount().negate());
        }
    }
}
