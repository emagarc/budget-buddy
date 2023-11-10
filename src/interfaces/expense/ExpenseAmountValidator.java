package interfaces.expense;

import exception.InvalidExpenseException;

@FunctionalInterface
public interface ExpenseAmountValidator {
    boolean notValidAmount(double amount) throws InvalidExpenseException;
}
