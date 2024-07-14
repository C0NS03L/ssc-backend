package com.moneytrackerbackend.repository;

import com.moneytrackerbackend.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByUserId(Long userId);
}