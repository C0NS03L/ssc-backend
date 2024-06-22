package com.moneytrackerbackend.repository;

import com.moneytrackerbackend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
