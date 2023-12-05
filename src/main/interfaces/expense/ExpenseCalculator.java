package main.interfaces.expense;

import main.entities.expenses.Expense;

public interface ExpenseCalculator {
    double calculateExpense(Expense expense);
    double calculateTotalExpense(Expense[] expenses);
}
