package com.moneytrackerbackend.repository;

import com.moneytrackerbackend.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.userId = :userId")
    Optional<BigDecimal> findTotalIncomeByUserId(Long userId);
}
