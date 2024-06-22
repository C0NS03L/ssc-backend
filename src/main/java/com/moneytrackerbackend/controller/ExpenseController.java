package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.Expense;
import com.moneytrackerbackend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }
}
