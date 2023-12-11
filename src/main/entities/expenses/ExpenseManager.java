package main.entities.expenses;

import main.dao.ExpenseDao;
import main.entities.transactions.TransactionManager;
import main.entities.user.User;
import main.exceptions.ExpenseCreationException;
import main.exceptions.ExpenseNotFoundException;
import main.interfaces.transaction.TransactionCategory;

import java.util.List;

import main.dao.ExpenseManagerDao;


public class ExpenseManager extends TransactionManager<Expense> {

    private final ExpenseDao expenseDao;

    public ExpenseManager(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    protected Expense createTransaction(double amount, int categoryId, String date, int userId) {
        try {
            Expense expense = new Expense(userId, amount, categoryId, date);
            expenseDao.insert(expense);
            return expense;
        } catch (Exception e) {
            throw new ExpenseCreationException("Error creating expense: " + e.getMessage());
        }
    }


    public List<Expense> getUserExpenses (User user) {
        return expenseManagerDao.getUserExpenses(user);
    }

    public List<Expense> getAllExpenses () {
        return expenseManagerDao.getAllExpenses();
    }


}
