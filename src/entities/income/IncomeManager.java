package entities.income;


import entities.transaction.TransactionManager;
import entities.user.User;
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

    public List<Income> getUserIncomes(User user) {
        return filterTransactionsByUser(user);
    }

    public List<Income> getAllIncomes() {
        return incomes;
    }
}
