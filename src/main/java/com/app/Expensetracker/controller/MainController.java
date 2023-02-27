package com.app.Expensetracker.controller;

import com.app.Expensetracker.model.Expense;
import com.app.Expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    @Autowired
    private ExpenseRepository repo;

    // Home Page
    @GetMapping("/")
    public String welcome() {
        return "<html><body>"
                + "<h1>WELCOME</h1>"
                + "</body></html>";
    }

    // Get All Notes
    @GetMapping("/expense/all")
    public List<Expense> getAllExpenses() {
        return repo.findAll();
    }

    // Get the company details by
    // ID
    @GetMapping("/expense/{id}")
    public Expense getExpenseById(
            @PathVariable(value = "id") int id) {
        return repo.findById(id);
    }

    @PostMapping("/expense")
    @ResponseStatus(HttpStatus.CREATED)
    public Expense addExpense(
            @RequestBody Expense expense) {
        return repo.save(expense);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExpense(
            @PathVariable(value = "id") int id) {
        repo.deleteById(id);
    }

    @PutMapping("/expense/{id}")
    public ResponseEntity<Object> updateExpense(
            @RequestBody Expense expense,
            @PathVariable int id) {

        Optional<Expense> companyRepo
                = Optional.ofNullable(
                repo.findById(id));

        if (!companyRepo.isPresent())
            return ResponseEntity
                    .notFound()
                    .build();

        expense.setId(id);

        repo.save(expense);

        return ResponseEntity
                .noContent()
                .build();
    }
}
