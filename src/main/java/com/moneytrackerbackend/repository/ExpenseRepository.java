package com.moneytrackerbackend.repository;

import com.moneytrackerbackend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
