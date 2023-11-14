package entities.income;


import entities.expense.Expense;
import entities.transaction.TransactionManager;
import entities.user.User;
import exception.ExpenseNotFoundException;
import exception.IncomeNotFoundException;
import interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;

public class IncomeManager extends TransactionManager<Income> {
    private List<Income> incomes;

    public IncomeManager() {
        incomes = new ArrayList<>();
    }


    @Override
    protected Income createTransaction(Double amount, TransactionCategory category, String date, User user) {
        return new Income(amount, (IncomeCategory) category, date, user);
    }

    @Override
    public Income getTransactionById(Integer incomeId) {
        try {
            return super.getTransactionById(incomeId);
        } catch (IncomeNotFoundException ex) {
            System.out.println("Income not found: " + ex.getMessage());
            throw ex;
        }
    }

    public List<Income> getUserIncomes(User user) {
        return filterTransactionsByUser(user);
    }

    public List<Income> getAllIncomes() {
        return incomes;
    }
}
