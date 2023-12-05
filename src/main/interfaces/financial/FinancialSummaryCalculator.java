package main.interfaces.financial;

import main.entities.summary.FinancialSummary;
import main.entities.user.User;

public interface FinancialSummaryCalculator {
    FinancialSummary calculateFinancialSummary(User user);
}
