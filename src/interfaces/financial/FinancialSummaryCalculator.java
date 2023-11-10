package interfaces.financial;

import entities.summary.FinancialSummary;
import entities.user.User;

public interface FinancialSummaryCalculator {
    FinancialSummary calculateFinancialSummary(User user);
}
