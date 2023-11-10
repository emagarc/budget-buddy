package interfaces.income;

import entities.income.IncomeCategory;
import entities.user.User;

public interface IncomeSource {
    Double getAmount();
    IncomeCategory getCategory();
    String getDate();
    User getUser();
}
