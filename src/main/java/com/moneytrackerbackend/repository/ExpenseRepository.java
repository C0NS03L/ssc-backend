package com.moneytrackerbackend.repository;

import com.moneytrackerbackend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);
    List<Expense> findByUserId(Long userId);
}