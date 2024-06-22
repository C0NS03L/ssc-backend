package com.moneytrackerbackend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseDto {

    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private Long categoryId;
    private Long userId;

    // Getters and setters
}
