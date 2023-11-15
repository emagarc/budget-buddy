package entities.expenses;

import entities.transactions.TransactionManager;
import entities.user.User;
import exceptions.ExpenseCreationException;
import exceptions.ExpenseNotFoundException;
import interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;


public class ExpenseManager extends TransactionManager<Expense> {


    @Override
    protected Expense createTransaction(Double amount, TransactionCategory category, String date, User user) {
        try {
            return new Expense(user, amount, (ExpenseCategory) category, date);
        } catch (Exception e) {
            throw new ExpenseCreationException("Error creating expense: " + e.getMessage());
        }
    }

    @Override
    public Expense getTransactionById(Integer expenseId) {
        try {
            return super.getTransactionById(expenseId);
        } catch (ExpenseNotFoundException ex) {
            System.out.println("Expense not found: " + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Expense removeTransaction(Integer expenseId) {
        try {
            return super.removeTransaction(expenseId);
        } catch (ExpenseNotFoundException ex) {
            System.out.println("Expense not found: " + ex.getMessage());
            throw ex;
        }
    }

    public List<Expense> getUserExpenses (User user) {
        return user.getExpenses();
    }

    public List<Expense> getAllExpenses () {
        return getAllTransactions();
    }


}
