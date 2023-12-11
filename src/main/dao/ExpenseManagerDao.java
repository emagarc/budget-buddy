package main.dao;
import main.entities.expenses.Expense;

import java.util.List;


public interface ExpenseManagerDao {
    void createExpense(Expense expense);
    Expense getExpenseById(int expenseId);
    Expense removeExpense(int expenseId);
    List<Expense> getUserExpenses(String userName);
    List<Expense> getAllExpenses();
}
