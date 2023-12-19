package main.dao;
import main.entities.incomes.Income;
import main.entities.summary.FinancialStatement;
import main.entities.summary.FinancialSummary;
import main.entities.user.User;

import java.util.List;


public interface IncomeManagerDao {
    Income getIncomeByIdForManager(int expenseId);
    Income removeIncome(int incomeId);
    List<Income> getUserIncomes(User user);
    List<Income> getAllIncomes();

    FinancialSummary getFinancialSummary(User user);
    FinancialStatement getFinancialStatement(User user);
}
