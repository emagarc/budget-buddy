package interfaces.income;

import entities.income.Income;
import entities.income.IncomeCategory;
import entities.user.User;

import java.util.List;

public interface IncomeInterface {
    void addIncome(User user, Double amount, IncomeCategory category, String date);
    List<Income> getUserIncomes(User user);
    List<Income> getAllIncomes();
}
