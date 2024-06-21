package com.moneytrackerbackend.service;

import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    public Income findById(Long id) {
        return incomeRepository.findById(id).orElse(null);
    }

    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    public void deleteById(Long id) {
        incomeRepository.deleteById(id);
    }
}
