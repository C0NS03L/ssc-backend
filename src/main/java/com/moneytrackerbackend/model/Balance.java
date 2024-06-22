package com.moneytrackerbackend.model;

import jakarta.persistence.*;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalIncome;
    private Double totalExpense;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    // Constructors
    public Balance() {
    }

    public Balance(AppUser user, Double totalIncome, Double totalExpense) {
        this.user = user;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Double getBalance() {
        return totalIncome - totalExpense;
    }
}
