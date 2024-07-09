package com.moneytrackerbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Expense> expenses = new HashSet<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Income> incomes = new HashSet<>();

    // Getters and setters
    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
