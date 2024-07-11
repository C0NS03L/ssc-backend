package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.Expense;
import com.moneytrackerbackend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }

    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(@PathVariable String category) {
        return expenseService.findByCategory(category);
    }
}
