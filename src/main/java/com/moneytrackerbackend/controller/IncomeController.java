package com.moneytrackerbackend.controller;

import com.moneytrackerbackend.model.Income;
import com.moneytrackerbackend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public List<Income> getAllIncome() {
        return incomeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Income> addIncome(@RequestBody Income income) {
        return ResponseEntity.ok(incomeService.save(income));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income income) {
        income.setId(id);
        return ResponseEntity.ok(incomeService.save(income));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        incomeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
