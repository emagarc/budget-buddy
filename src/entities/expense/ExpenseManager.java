package entities.expense;

import entities.transaction.TransactionManager;
import entities.user.User;

import java.util.ArrayList;
import java.util.List;


public class ExpenseManager extends TransactionManager<Expense> {
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(User user, Double amount, ExpenseCategory category, String date) {
        Expense expense  = new Expense(user, amount, category, date);
        user.getExpenses().add(expense);
        expenses.add(expense);
    }

    public List<Expense> getUserExpenses (User user) {
        return user.getExpenses();
    }

    public List<Expense> getAllExpenses () {
        return expenses;
    }


}
