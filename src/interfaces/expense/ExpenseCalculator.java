package interfaces.expense;

import entities.expense.Expense;

public interface ExpenseCalculator {
    double calculateExpense(Expense expense);
    double calculateTotalExpense(Expense[] expenses);
}
