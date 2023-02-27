package com.app.Expensetracker.repository;

import com.app.Expensetracker.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Integer> {

    Expense findById(int id);

    List<Expense> findAll();

    void deleteById(int id);
}
