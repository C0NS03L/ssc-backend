package com.moneytrackerbackend.repository;

import com.moneytrackerbackend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.userId = :userId")
    Optional<BigDecimal> findTotalExpenseByUserId(Long userId);
}
