package entities.expense;

import entities.transaction.TransactionManager;
import entities.user.User;
import interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;


public class ExpenseManager extends TransactionManager<Expense> {
    private List<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }


    @Override
    protected Expense createTransaction(Double amount, TransactionCategory category, String date, User user) {
        return new Expense(user, amount, (ExpenseCategory) category, date);
    }

    public List<Expense> getUserExpenses (User user) {
        return user.getExpenses();
    }

    public List<Expense> getAllExpenses () {
        return expenses;
    }


}
