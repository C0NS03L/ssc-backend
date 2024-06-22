package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        return incomeService.save(income);
    }
}
