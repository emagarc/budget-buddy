package main.entities.expenses;

import main.entities.transactions.TransactionManager;
import main.entities.user.User;
import main.exceptions.ExpenseCreationException;
import main.exceptions.ExpenseNotFoundException;
import main.interfaces.transaction.TransactionCategory;

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
