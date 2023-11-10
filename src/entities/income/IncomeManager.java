package entities.income;

import entities.user.User;
import interfaces.income.IncomeInterface;

import java.util.ArrayList;
import java.util.List;

public class IncomeManager implements IncomeInterface {
    private List<Income> incomes;

    public IncomeManager() {
        incomes = new ArrayList<>();
    }


    public void addIncome(User user, Double amount, IncomeCategory category, String date) {
        Income income = new Income(amount, category, date, user);
        user.getIncomes().add(income);
        incomes.add(income);
    }

    public List<Income> getUserIncomes(User user) {
        return user.getIncomes();
    }

    public List<Income> getAllIncomes() {
        return incomes;
    }
}
