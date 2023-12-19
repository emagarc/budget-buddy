package main.dao;
import main.entities.expenses.Expense;
import main.entities.summary.FinancialStatement;
import main.entities.summary.FinancialSummary;
import main.entities.user.User;

import java.util.List;


public interface ExpenseManagerDao {
    Expense getExpenseByIdForManager(int expenseId);
    Expense removeExpense(int expenseId);
    List<Expense> getUserExpenses(User user);
    List<Expense> getAllExpenses();

    FinancialSummary getFinancialSummary(User user);
    FinancialStatement getFinancialStatement(User user);
}
