package entities.expense;

import entities.user.User;

import java.util.ArrayList;
import java.util.List;


public class ExpenseManager {
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(User user, Double amount, String category, String date) {
        Expense expense  = new Expense(amount, category, date);
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
